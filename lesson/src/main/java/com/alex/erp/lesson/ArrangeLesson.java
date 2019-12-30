package com.alex.erp.lesson;

import com.alex.erp.basic.dic.ResultCode;
import com.alex.erp.basic.vo.Result;
import com.alex.erp.basic.vo.factory.ResultFactory;
import com.alex.erp.lesson.dic.Constant;
import com.alex.erp.lesson.entity.Condition;
import com.alex.erp.lesson.entity.CoursePlan;
import com.alex.erp.lesson.entity.Lesson;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @version 0.0.1
 * @Description
 * @Author Alex ZY Liang
 * @Date 2019-10-13 4:25 PM
 */
public class ArrangeLesson {
    private  List<Condition> teachers;
    private  List<Condition> classRooms;
    private  List<Condition> segments;

    private List<CoursePlan> cours;
    private List<CoursePlan> toArrangeCours;


    private List<Lesson> arrangedLesson = new ArrayList();

    private void loadTeachers(){}
    private void loadClassRooms(){}
    private void loadSegments(){}
    private void loadCourse(){}


    private void loadBasicData(){
        this.loadClassRooms();
        this.loadSegments();
        this.loadTeachers();

        this.loadCourse();
    }

    private Result canArrangeLesson(){

        List<CoursePlan> list = this.cours.stream().filter(item -> !item.canArrangeCourse()).collect(Collectors.toList());
        if(list.size()>0){
            return ResultFactory.buildFailResult(ResultCode.OTHER_FAILED.getCode(),Constant.CAN_NOT_ARRANGE_LESSON,list);
        }

        return  ResultFactory.buildSuccessResult();
    }

    private void caculatePriority(){
        cours.stream().forEach(CoursePlan::calculatePriority);
        toArrangeCours = cours.stream().filter(
                a -> a.getPriority()>0).collect(Collectors.toList());
        toArrangeCours.sort(Comparator.comparingInt(CoursePlan::getPriority).reversed());
    }

    private void arrangeLessonAuto(){
        int tryTimes=1;
        do{
            toArrangeCours.stream().map(m -> this.arrangedLesson.addAll(m.generateLesson()));
            tryTimes++;
        }while(tryTimes<=Constant.TRY_TIMES_OF_ARRANGE && validateConflict() );
    }

    private boolean validateConflict(){

        List<CoursePlan> cours = ArrangeUtils.getConflictCourse(arrangedLesson);
        if(cours.size()==0) {
            return false;
        }
        List<Lesson> lessons = new ArrayList<>();
        this.arrangedLesson.stream().map(
                item -> cours.contains(item.getCoursePlan()) ? lessons.add(null) : lessons.add(item));
        this.arrangedLesson = lessons;
        this.toArrangeCours = cours;

        return true;
    }

    public boolean arrangeLesson(){
        loadBasicData();
        if(Objects.equals( canArrangeLesson().getCode(),ResultCode.SUCCESS.getCode())){
            caculatePriority();
            arrangeLessonAuto();
        }
        return true;
    }

}
