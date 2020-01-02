package com.alex.erp.lesson.gens;

import com.alex.erp.lesson.ArrangeUtils;
import com.alex.erp.lesson.dic.Constant;
import com.alex.erp.lesson.dic.MutationAndCrossPositionEnum;
import com.alex.erp.lesson.entity.*;
import com.alex.erp.lesson.utils.CommonUtils;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @version 0.0.1
 * @Description
 * @Author Alex ZY Liang
 * @Date 2019-12-18 3:16 PM
 */
@Data
public class LessonGens {
    //种群大小
    private static final int populationSize = 32;
    //迭代次数
    private static final int generationTimes = 500;

    //精英数量
    private static final int eliteNum = 8;

    //变异概率
    private static final double pcl=0.7d;
    private static final double pch=0.95d;

    //
    private static final String generateType=Constant.GENERATE_TYPE_BEST;

    private static Random rand = new Random();


    //基因序列
    public List<Gene> population;

    public List<Gene> elitePopulation;
    //适应度
    public int[] fitness;



    private int generation = 0; 	//染色体代号
    public static final int GENE = 46; 		//基因数
    private double bestfitness = Double.MAX_VALUE;  //函数最优解
    private int bestgenerations;   	//所有子代与父代中最好的染色体
    private String beststr;   		//最优解的染色体的二进制码

    @Data
    static class Gene{
        private int fitness = 0;

        private int softFitness = 0;
        private List<Lesson> lessons;

        public Gene(List<Lesson> lessons) {
            this.lessons = lessons;
        }
    }

    /**
     * 初始化一条染色体
     */
    private Gene initGenes(){
        List<Lesson> lessons = new ArrayList<>();
        List<CoursePlan> coursePlans = new ArrayList<>();
        coursePlans.parallelStream().forEach(p->lessons.addAll(p.generateLesson()));
        Gene gene = new Gene(lessons);
        return gene;
    }

    /**
     * 初始化一个种群
     */
    private void initPop() {
        IntStream.range(1,populationSize).forEach(i->{
            population.add(initGenes());
            fitness[i]=0;
        });
    }

    /**
     * 将染色体转换成x,y变量的值
     */
    private Gene calculateFitnessValue(Gene gene) {
        List<Lesson> lessons = gene.getLessons();
        List<LessonConflict> hardConflicts= ArrangeUtils.validateHardCondition(lessons);
        List<LessonConflict> softConflicts= ArrangeUtils.validateSoftCondition(lessons);

        gene.setFitness(hardConflicts.size());
        gene.setSoftFitness(softConflicts.size());
        return gene;
    }

    private String fetchGroupKey(Segment segment,EducationResource resource){
        return segment.getWeekDay()+Constant.JOIN_STRING+segment.getSlot()+Constant.JOIN_STRING+resource.getId();
    }



    /**
     * 轮盘选择
     * 计算群体上每个个体的适应度值;
     * 按由个体适应度值所决定的某个规则选择将进入下一代的个体;
     */
    private void select() {
        this.elitePopulation = this.getPopulation().parallelStream()
                .map(gene-> calculateFitnessValue(gene))
                .sorted(Comparator.comparing(Gene::getFitness).thenComparing(Gene::getSoftFitness))
                .limit(LessonGens.eliteNum)
                .collect(Collectors.toList());
    }


    private void reproduce(){
        this.getPopulation().clear();
        this.getPopulation().addAll(this.getElitePopulation());

        while(this.population.size()<LessonGens.populationSize){

            List<Lesson> lessons;
            double _rate = rand.nextDouble();
            if( LessonGens.pcl<_rate && _rate<LessonGens.pch){
                lessons = this.cross();
            }else{
                lessons = this.mutation();
            }
            Gene gene = new Gene(lessons);
            this.getPopulation().add(gene);
        }

    }

    /**
     * 交叉操作 交叉率为60%，平均为60%的染色体进行交叉
     */
    private List<Lesson> cross() {
        try{
            List<Lesson> fatherGenes = this.getPopulation().get(rand.nextInt(LessonGens.eliteNum)).getLessons();
            List<Lesson> motherGenes = this.getPopulation().get(rand.nextInt(LessonGens.eliteNum)).getLessons();
            List<Lesson> childGenes = CommonUtils.deepCopyList(fatherGenes);
            int mutatePosition = rand.nextInt(MutationAndCrossPositionEnum.values().length)+1;
            childGenes.parallelStream().forEach(o->{
                String _id = o.getId();
                Lesson childGene = o;
                motherGenes.parallelStream().filter(l->l.getId().equals(_id)).findFirst().ifPresent(
                        t->{
                            switch (MutationAndCrossPositionEnum.getByValue(mutatePosition)){
                                case WEEKDAY:
                                    childGene.getSegment().setWeekDay(t.getSegment().getWeekDay());
                                    break;
                                case TEACHER:
                                    childGene.setTeacher(t.getTeacher());
                                    break;
                                case ASSISTANT:
//                                    if(!(childGene.getClassRoom() instanceof NativeClassRoom)){
//                                        childGene.setClassRoom(t.getClassRoom());
//                                    }

                                    childGene.setAssistant(t.getAssistant());
                                    break;
                                case CLASSROOM:
                                    childGene.setClassRoom(t.getClassRoom());
                                    break;
                                case SLOT:
                                    childGene.getSegment().setSlot(t.getSegment().getSlot());
                                default:
                                    break;
                            }
                        }
                );
            });
            return childGenes;
        }catch (Exception e){

        }
        return null;
    }

    /**
     * 基因突变操作 基因变异
     */
    private List<Lesson> mutation() {

        try{
            List<Lesson> fatherGene = this.getPopulation().get(rand.nextInt(LessonGens.eliteNum)).getLessons();
            List<Lesson> childGene = CommonUtils.deepCopyList(fatherGene);
            childGene.stream().forEach(o->{
                int mutatePosition = rand.nextInt(MutationAndCrossPositionEnum.values().length)+1;
                CoursePlan coursePlan = o.getCoursePlan();
                switch (MutationAndCrossPositionEnum.getByValue(mutatePosition)){
                    case WEEKDAY:
                        mutation_changeWeekDay(o);
                        break;
                    case TEACHER:
                        Teacher teacher = o.getCoursePlan().generateTeacher();
                        childGene.parallelStream().filter(l->l.getCoursePlan().equals(coursePlan)).forEach(l->l.setTeacher(teacher));
                        break;
                    case ASSISTANT:
                        List<Teacher> assistants = o.getCoursePlan().generateAssistant();
                        childGene.parallelStream().filter(l->l.getCoursePlan().equals(coursePlan)).forEach(l->l.setAssistant(assistants));
                        break;
                    case CLASSROOM:
                        o.setClassRoom(o.getCoursePlan().generateClassroom());
                        break;
                    case SLOT:
                        mutation_changeSlot(o);
                        break;
                    default:
                        break;
                }
            });
            return childGene;

        }catch (Exception e){

        }
        return null;
    }

    private void mutation_changeWeekDay(Lesson o){
        int _weekDay =rand.nextInt(5)+1;
        o.getSegment().setWeekDay(_weekDay);
        if(o instanceof ContinuedLesson){
            ContinuedLesson _c =(ContinuedLesson) o;
            //连堂课，修改相关所有课程
            while (_c.getPreLesson()!=null){
                _c=_c.getPreLesson();
                _c.getSegment().setWeekDay(_weekDay);
            }
            _c =(ContinuedLesson) o;
            while (_c.getNextLesson()!=null){
                _c=_c.getNextLesson();
                _c.getSegment().setWeekDay(_weekDay);
            }
        }

    }

    private void mutation_changeSlot(Lesson o){
        if(o instanceof ContinuedLesson){

            while(((ContinuedLesson) o).getPreLesson()!=null){
                o=((ContinuedLesson) o).getPreLesson();
            }
            ContinuedCoursePlan continuedCoursePlan = ((ContinuedLesson) o).getCoursePlan();
            int _slot = rand.nextInt(Constant.MAX_SOLT-continuedCoursePlan.getRelatedCoursePlans().size()+1)+1;
            do{
                o.getSegment().setSlot(_slot);
                _slot++;
                o=((ContinuedLesson) o).getNextLesson();
            } while(o!=null);
        }else{

            o.getSegment().setSlot(rand.nextInt(Constant.MAX_SOLT)+1);
        }
    }

    public static void main(String args[]) {
        LessonGens gens = new LessonGens();
        gens.initPop(); //产生初始种群

        //迭代次数
        for (int i = 0; i < LessonGens.generationTimes; i++) {
            gens.select();
            if(Constant.GENERATE_TYPE_SPEED.equalsIgnoreCase(LessonGens.generateType) &&
                    gens.getElitePopulation().get(0).getFitness()==0){
                break;
            }
            gens.reproduce();
        }

//            List<Lesson> list = LessonGens.mockData();
////            Map<String,List<Lesson>> map =
//        IntSummaryStatistics stats = list.parallelStream().collect(Collectors.groupingBy(o->o.getTeacher()))
//            .entrySet().stream().filter(x->x.getValue().size()>1).mapToInt(x->x.getValue().size()).summaryStatistics();
//        System.out.println(stats.getCount());
//        System.out.println(stats.getSum());
////                    .forEach(o->System.out.println(o.getValue().size()))
////            .entrySet().stream().forEach(o->System.out.println(o.getValue().size()))
// ;
//        System.out.println("end");


    }


    private static List<Lesson> mockData(){
        Lesson l1 = new Lesson(); Teacher t1 = new Teacher(); t1.setId("1"); l1.setTeacher(t1);
        Lesson l2 = new Lesson(); Teacher t2 = new Teacher(); t2.setId("2"); l2.setTeacher(t2);
        Lesson l3 = new Lesson(); Teacher t3 = new Teacher(); t3.setId("2"); l3.setTeacher(t3);
        Lesson l4 = new Lesson(); Teacher t4 = new Teacher(); t4.setId("3"); l4.setTeacher(t4);
        Lesson l5 = new Lesson(); Teacher t5 = new Teacher(); t5.setId("3"); l5.setTeacher(t5);
        Lesson l6 = new Lesson(); Teacher t6 = new Teacher(); t6.setId("3"); l6.setTeacher(t6);
        Lesson l7 = new Lesson(); Teacher t7 = new Teacher(); t7.setId("4"); l7.setTeacher(t7);
        Lesson l8 = new Lesson(); Teacher t8 = new Teacher(); t8.setId("4"); l8.setTeacher(t8);
        Lesson l9 = new Lesson(); Teacher t9 = new Teacher(); t9.setId("4"); l9.setTeacher(t9);
        Lesson l10 = new Lesson(); Teacher t10= new Teacher(); t10.setId("4"); l10.setTeacher(t10);
        Lesson l11 = new Lesson(); Teacher t11= new Teacher(); t11.setId("5"); l11.setTeacher(t11);

        List<Lesson> lessons = new ArrayList<>();
        lessons.add(l1);
        lessons.add(l2);
        lessons.add(l3);
        lessons.add(l4);
        lessons.add(l5);
        lessons.add(l6);
        lessons.add(l7);
        lessons.add(l8);
        lessons.add(l9);
        lessons.add(l10);
        lessons.add(l11);

        return lessons;
    }
}

