package com.alex.erp.lesson.gens;

import com.alex.erp.lesson.dic.Constant;
import com.alex.erp.lesson.entity.CoursePlan;
import com.alex.erp.lesson.entity.Lesson;
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

    //
    private static final double pcl=0.7d;
    private static final double pch=0.95d;

    private static final String generateType=Constant.GENERATE_TYPE_BEST;


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
        gene.setFitness(1);
        return gene;
    }


    private void calculateFitnessValue() {

        for (int j = 0; j < fitness.length; j++) {
            fitness[j] = 100;
        }

        for (int i = 0; i < TSPDATA.SPECIES_Num; i++) {
            for (int j = 0; j < TSPDATA.LessonNum - 1; j++) {

                Lessons le = (population[i][j]);
                Lessons lec = (population[i][j + 1]);
                if (le.weekDay.equalsIgnoreCase(lec.weekDay) && le.course != "9") {
                    if (le.teacher.equalsIgnoreCase(lec.teacher) || le.course.equalsIgnoreCase(lec.course) || le.classm.equalsIgnoreCase(lec.classm)) {
                        fitness[i]--;

                    }
                }

            }
        }
    }
    /**
     * 轮盘选择
     * 计算群体上每个个体的适应度值;
     * 按由个体适应度值所决定的某个规则选择将进入下一代的个体;
     */
    private void select() {
        this.elitePopulation = this.getPopulation().stream()
                .map(gene-> calculateFitnessValue(gene))
                .sorted(Comparator.comparing(Gene::getFitness))
                .limit(LessonGens.eliteNum)
                .collect(Collectors.toList());
    }

    /**
     * 交叉操作 交叉率为60%，平均为60%的染色体进行交叉
     */
    private List<Lesson> cross() {
//        String temp1, temp2;
//        for (int i = 0; i < ChrNum; i++) {
//            if (Math.random() < 0.60) {
//                int pos = (int)(Math.random()*GENE)+1;     //pos位点前后二进制串交叉
//                temp1 = ipop[i].substring(0, pos) + ipop[(i + 1) % ChrNum].substring(pos);
//                temp2 = ipop[(i + 1) % ChrNum].substring(0, pos) + ipop[i].substring(pos);
//                ipop[i] = temp1;
//                ipop[(i + 1) / ChrNum] = temp2;
//            }
//        }
        Random rand = new Random();

        for (int i = 0; i < TSPDATA.SPECIES_Num; i++)
        {
            float rate = (float)rand.nextDouble();
            for (int j = 0; j < TSPDATA.LessonNum-1; j++)
            {
                if (rate > TSPDATA.pcl && rate < TSPDATA.pch)
                {
//string str = population[i+1, j];
                    Lessons le1 = population[i][j];
                    Lessons le2 = population[i][ j+1];
                    if (le1.weekDay != "-1" && le2.weekDay != "-1")
                    {
                        String s, k, g;
                        s = le1.teacher;
                        k = le1.course;
                        g = le1.classm;
                        le1.teacher = le2.teacher;
                        le1.course = le2.course;
                        le1.classm = le2.classm;
                        le2.classm = g;
                        le2.course = k;
                        le2.teacher = s;
                        population[i][j] = le1;
                        population[i][ j + 1] = le2;
                    }
                }
            }
        }
        return null;
    }

    /**
     * 基因突变操作 1%基因变异
     */
    private List<Lesson> mutation() {


        Random rand = new Random();
        float rate = (float)rand.nextDouble();
        for (int i = 0; i < TSPDATA.SPECIES_Num; i++)
        {
            for (int j = 0; j < TSPDATA.LessonNum - 1; j++)
            {
                if (rate < TSPDATA.pm)
                {

                    Lessons l1 = population[i][j];
                    l1.teacher = TSPDATA.Teacher[rand.nextInt(TSPDATA.Teacher.length)];
                    l1.course = TSPDATA.Course[rand.nextInt(TSPDATA.Course.length)];
                    l1.classm = TSPDATA.Classm[rand.nextInt(TSPDATA.Classm.length)];
                    population[i][j] = l1;
                }
            }
        }

//        for (int i = 0; i < 4; i++) {
//            int num = (int) (Math.random() * GENE * ChrNum + 1);
//            int chromosomeNum = (int) (num / GENE) + 1; // 染色体号
//
//            int mutationNum = num - (chromosomeNum - 1) * GENE; // 基因号
//            if (mutationNum == 0)
//                mutationNum = 1;
//            chromosomeNum = chromosomeNum - 1;
//            if (chromosomeNum >= ChrNum)
//                chromosomeNum = 9;
//            String temp;
//            String a;   //记录变异位点变异后的编码
//            if (ipop[chromosomeNum].charAt(mutationNum - 1) == '0') {    //当变异位点为0时
//                a = "1";
//            } else {
//                a = "0";
//            }
//            //当变异位点在首、中段和尾时的突变情况
//            if (mutationNum == 1) {
//                temp = a + ipop[chromosomeNum].substring(mutationNum);
//            } else {
//                if (mutationNum != GENE) {
//                    temp = ipop[chromosomeNum].substring(0, mutationNum -1) + a
//                            + ipop[chromosomeNum].substring(mutationNum);
//                } else {
//                    temp = ipop[chromosomeNum].substring(0, mutationNum - 1) + a;
//                }
//            }
//            //记录下变异后的染色体
//            ipop[chromosomeNum] = temp;
//        }
        return null;
    }

    public static void main(String args[]) {


        LessonGens tryer = new LessonGens();
        tryer.initPop(); //产生初始种群

        //迭代次数
        for (int i = 0; i < LessonGens.generationTimes; i++) {
            tryer.select();
            if(Constant.GENERATE_TYPE_SPEED.equalsIgnoreCase(LessonGens.generateType) &&
                    tryer.getElitePopulation().get(0).getFitness()==0){
                break;
            }
            while(tryer.population.size()<LessonGens.populationSize){

                Random rand = new Random();
                List<Lesson> lessons;
                double _rate = rand.nextDouble();
                if( LessonGens.pcl<_rate && _rate<LessonGens.pch){
                    lessons = tryer.cross();
                }else{
                    lessons = tryer.mutation();
                }
                Gene gene = new Gene(lessons);
                tryer.getPopulation().add(gene);
            }
        }
    }
}

