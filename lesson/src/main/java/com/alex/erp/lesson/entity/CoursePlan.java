package com.alex.erp.lesson.entity;

import com.alex.erp.lesson.ArrangeUtils;
import com.alex.erp.lesson.dic.Constant;
import com.alex.erp.lesson.dic.CourseEnum;
import com.alex.erp.lesson.utils.CommonUtils;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @version 0.0.1
 * @Description
 * @Author Alex ZY Liang
 * @Date 2019-10-12 5:21 PM
 */
@Data
public class CoursePlan implements Cloneable{

//    protected String name;
//    protected String alias;
//    protected String code;
    protected String id;

    protected Course course;

    /**
     * 课程模式，单双周
     */
    protected Enum cycleType = CourseEnum.CYCLE_TYPE_EVERY;

    /**
     *每周上几节课
     */
    protected int  quantity;

    protected int assistantNum;

    protected List<Students> students;


//    private boolean combinedCourse = false;

    /**
     * 是否组合课程
     *
     */
//    private boolean continuedCourse = false;
//    private CoursePlan nextCourse;
//    private CoursePlan preCourse;


    protected List<ClassRoom> fittedClassRooms = new ArrayList<>();
    protected List<Segment> fittedSegments= new ArrayList<>();
    protected List<Teacher> fittedTeachers= new ArrayList<>();
    protected List<Teacher> fittedAssistant =  new ArrayList<>();
    protected List<Segment> availableSegments;
    protected int priority = 0;


    public int getPriority(){
        if(this.priority==0){
            calculatePriority();
        }
        return this.priority;
    }

//
//    public List<ClassRoom> getFittedClassRooms(){
//
//    }
//
//    public List<Condition> getFittedClassRooms(int fitType){
//        return ArrangeUtils.getFitnessList(this.getFittedClassRooms(),fitType);
//    }
//
//
//    public List<Condition> getFittedSegments(int fitType){
//        return ArrangeUtils.getFitnessList(this.fittedSegments,fitType);
//    }
//
//
//    public List<Condition> getFittedTeachers(int fitType){
//        return ArrangeUtils.getFitnessList(this.fittedTeachers,fitType);
//
//    }

//    public void calculateAvailableSegment(int continuedNum){
//        this.availableSegments =  CommonUtils.getIntersectedList(
//                getAllAvailableSegmentOfTeachers(),
//                getAllAvailableSegmentOfClassRoom(),
//                fittedSegments.stream().filter(o->((Segment)o).getSlot()<Constant.MAX_SOLT-continuedNum+1).collect(Collectors.toList()));
//    }




    public void calculateAvailableSegment(){
        this.availableSegments =  CommonUtils.getIntersectedList(
                getAllAvailableSegmentOfTeachers(),
                getAllAvailableSegmentOfClassRoom(),
                fittedSegments);
    }

    public List<Segment> getAvailableSegments(){
        if(this.availableSegments == null){
            this.calculateAvailableSegment();
        }
        if(this.availableSegments.size()==0){
            return this.fittedSegments;
        }
        return this.availableSegments;
    }

//    public List<Segment> getAvailableSegments(int continuedNum){
//        if(this.availableSegments == null){
//            this.calculateAvailableSegment(continuedNum);
//        }
//        return this.availableSegments;
//    }


    protected List<? extends Condition> getAllAvailableSegmentOfTeachers(){
        List<Condition> list = new ArrayList<>();
        list.addAll(getAllAvailableSegment( this.fittedTeachers));
        if(this.getAssistantNum()>0){
            list.addAll(this.fittedAssistant);
            list = CommonUtils.listRemoveDuplicate(list);
        }
        return list;
    }

    protected List<? extends Condition> getAllAvailableSegmentOfClassRoom(){
        return getAllAvailableSegment( this.fittedClassRooms);
    }

    private List<? extends Condition> getAllAvailableSegment(List<? extends EducationResource> educationResourceList){
        List<Segment> list = new ArrayList<>();
        for (Condition c: educationResourceList) {
            EducationResource r = (EducationResource) c;
            list.addAll(r.getAvaliableSegment());
        }

        list = CommonUtils.listRemoveDuplicate(list);
        return list;
    }

    public void calculatePriority(){

       int priority ;
//        priority =
//                Constant.WEIGHT_OF_CLASSROOM * getPriorityFactor(this.fittedClassRooms)*
//                Constant.WEIGHT_OF_SEGMENT * getPriorityFactor(this.fittedSegments)*
//                Constant.WEIGHT_OF_TEACHER * getPriorityFactor(this.fittedTeachers);

        priority = this.getAvailableSegments().size()/ArrangeUtils.getAllSegment().size()*100;

        this.priority = priority;

    }

    private int getPriorityFactor(List<? extends Condition> list){

        int requiredNum = ArrangeUtils.getFitnessList(list,CourseEnum.REQUIRED.getCode()).size();
        if(requiredNum>0){
            return requiredNum;
        }
        int disRequiredNum = ArrangeUtils.getFitnessList(list,CourseEnum.DISREQUIRED.getCode()).size();
        if(disRequiredNum>0){
            return list.size()-disRequiredNum;
        }
        return 1;
    }

    public List<Lesson> generateLesson(){
        Teacher teacher = this.generateTeacher();
        ClassRoom classRoom = this.generateClassroom();

        List<Lesson> list = new ArrayList<>();
        for (int i = 0; i < this.quantity; i++) {
            Segment segment = this.generateSegments();
            Lesson lesson = new Lesson(this, segment, classRoom, teacher,this.getStudents(),this.getCycleType());
           list.add(lesson);
        }
        return list;
    }


    public Segment generateSegments(){

        Segment segment;
        Random random = new Random();
        List<Segment> availableResources;
        if(this.getAvailableSegments().size()>0){
            availableResources =this.getAvailableSegments();
        }else{
            availableResources = this.getCourse().getFittedSegments();
        }
        int n = random.nextInt(availableResources.size());
        segment = availableResources.get(n);
        return segment;

    }


    public Teacher generateTeacher(){

        Teacher teacher;
        Random random = new Random();
        List<Teacher> availableResources;
        if(this.getFittedTeachers().size()>0){
            availableResources =this.getFittedTeachers();
        }else{
            availableResources = this.getCourse().getFittedTeachers();
        }
        int n = random.nextInt(availableResources.size());
        teacher = availableResources.get(n);
        return teacher;

    }

    public List<Teacher> generateAssistant(){
        List<Teacher> assistants = new ArrayList<>();
        for (int i = 0; i < this.getAssistantNum(); i++) {
            Teacher teacher;
            Random random = new Random();
            List<Teacher> availableResources;
            if(this.getFittedAssistant().size()>0){
                availableResources =this.getFittedAssistant();
            }else{
                availableResources = this.getCourse().getFittedAssistant();
            }
            int n = random.nextInt(availableResources.size());
            teacher = availableResources.get(n);
            assistants.add(teacher);
        }
        return assistants;

    }


    public ClassRoom generateClassroom(){

        ClassRoom classRoom;
        Random random = new Random();
        List<ClassRoom> availableResources;
        if(this.getFittedClassRooms().size()>0){
            availableResources =this.getFittedClassRooms();
        }else{
            availableResources = this.getCourse().getFittedClassRooms();
        }
        int n = random.nextInt(availableResources.size());
        classRoom = availableResources.get(n);
        return classRoom;

    }



    public boolean  canArrangeCourse(){
        return this.getAvailableSegments().size()>this.getQuantity();
    }


//    public boolean equals(CoursePlan coursePlan){
//
//        return this.code.equalsIgnoreCase(coursePlan.getCode());
//    }

//    @Override
//    protected Object clone() throws CloneNotSupportedException {
//        CoursePlan plan = (CoursePlan) super.clone();
//        plan.course = course.
//        p.address = (Address) address.clone();
//        return p;
//    }

}
