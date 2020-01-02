package com.alex.erp.lesson;

import com.alex.erp.lesson.dic.Constant;
import com.alex.erp.lesson.entity.*;
import com.alex.erp.lesson.utils.CommonUtils;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @version 0.0.1
 * @Description
 * @Author Alex ZY Liang
 * @Date 2019-10-13 6:02 PM
 */
public class ArrangeUtils {
    private static List<Segment> weekSegment = null;
    static {
        generateWeekSegment();
    }

    public static List<LessonConflict> validateHardCondition(List<Lesson> lessons) {
        List<LessonConflict> lessonConflict = new ArrayList<>();
        lessonConflict.addAll(validateClassroom(lessons));
        lessonConflict.addAll(validateTeacher(lessons));
        lessonConflict.addAll(validateStudents(lessons));


        return lessonConflict;

    }
    public static List<LessonConflict> validateSoftCondition(List<Lesson> lessons) {
        List<LessonConflict> lessonConflict = new ArrayList<>();
        lessonConflict.addAll(validateClassroom(lessons));
        lessonConflict.addAll(validateTeacher(lessons));
        lessonConflict.addAll(validateStudents(lessons));


        return lessonConflict;

    }

    private static List<LessonConflict> validateClassroom(List<Lesson> lessons){
        List<LessonConflict> lessonConflict = new ArrayList<>();
                lessons.parallelStream()
                .filter(o -> !(o.getClassRoom() instanceof NativeClassRoom))
                        .collect(Collectors.groupingBy(o -> o.getSegment().toString()+Constant.JOIN_STRING+o.getClassRoom().getId()))
                .entrySet().parallelStream().forEach(x->{
                    List<Lesson> lessonList =  x.getValue();
                    if(x.getValue().size()>1){
                        LessonConflict conflict = new LessonConflict();
                        conflict.setConflictSegment(lessonList.get(0).getSegment());
                        lessonList.parallelStream().forEach(o->conflict.getConflictResources().add(o.getClassRoom()));
                        lessonConflict.add(conflict);
                    }
                });
        return lessonConflict;
    }


    private static List<LessonConflict> validateTeacher(List<Lesson> lessons){


        List<Lesson> lessonList =  new ArrayList<>();
        lessonList.addAll(lessons);
        List<Lesson> assistantLessons = lessons.parallelStream().filter(o->o.getAssistant()!=null).collect(Collectors.toList());
        if(assistantLessons.size()!=0){
            assistantLessons.parallelStream().forEach(o->{
                Segment segment = o.getSegment();
                o.getAssistant().parallelStream().forEach(x->{
                    Lesson lesson = new Lesson();
                    lesson.setSegment(segment);
                    lesson.setTeacher(x);
                    lessonList.add(lesson);
                });
            });
        }


        List<LessonConflict> lessonConflict = new ArrayList<>();
        lessons.parallelStream()
                .collect(Collectors.groupingBy(o -> o.getSegment().toString()+Constant.JOIN_STRING+o.getTeacher().getId()))
                .entrySet().parallelStream().forEach(x->{
            List<Lesson> _lessons =  x.getValue();
            if(_lessons.size()>1){
                LessonConflict conflict = new LessonConflict();
                conflict.setConflictSegment(_lessons.get(0).getSegment());
                _lessons.parallelStream().forEach(o->conflict.getConflictResources().add(o.getTeacher()));
                lessonConflict.add(conflict);
            }
        });
        return lessonConflict;
    }



    private static List<LessonConflict> validateStudents(List<Lesson> lessons){


        List<Lesson> lessonList =  new ArrayList<>();
        lessonList.addAll(lessons);
        List<Lesson> combinedLessons = lessons.parallelStream().filter(o->o.getStudents().size()>1).collect(Collectors.toList());
        if(combinedLessons.size()!=0){
            lessons.removeAll(combinedLessons);
            combinedLessons.parallelStream().forEach(o->{

                Segment segment = o.getSegment();
                o.getStudents().parallelStream().forEach(x->{
                    Lesson lesson = new Lesson();
                    lesson.setSegment(segment);
                    List<Students> list = new ArrayList<>();
                    list.add(x);
                    lesson.setStudents(list);
                    lessonList.add(lesson);
                });
            });
        }


        List<LessonConflict> lessonConflict = new ArrayList<>();
        lessons.parallelStream()
                .collect(Collectors.groupingBy(o -> o.getSegment().toString()+Constant.JOIN_STRING+o.getStudents().get(0).toString()))
                .entrySet().parallelStream().forEach(x->{
            List<Lesson> _lessons =  x.getValue();
            if(_lessons.size()>1){
                LessonConflict conflict = new LessonConflict();
                conflict.setConflictSegment(_lessons.get(0).getSegment());
                _lessons.parallelStream().forEach(o->conflict.getConflictResources().add(o.getStudents().get(0)));
                lessonConflict.add(conflict);
            }
        });
        return lessonConflict;
    }



//    private static void validateSoftCondition(List<Lesson> lessons){
//
//    }

//    private static void validateHardCondition(List<Lesson> lessons){
//        List<CoursePlan> conflictList = new ArrayList<>();
//
//        try {
//
//            Class clz = Lesson.class;
//
//            //同一个教师在同一个时间有两节课
//            Method method = clz.getMethod("getTeacher");
//            List<CoursePlan> teacherConflict = getConflictCourses(lessons, method);
//
//            //同一个教室在同一个时间有两节课
//            method = clz.getMethod("getClassRoom");
//            List<CoursePlan> classRoomConflict = getConflictCourses(lessons, method);
//
//            //同一个专业方向在同一个时间有两节课
//            method = clz.getMethod("getSpecialityClasses");
//
//            List<CoursePlan> classConflict = getConflictCourses(lessons, method);
//
//            conflictList.addAll(classRoomConflict);
//            conflictList.addAll(teacherConflict);
//            conflictList.addAll(classConflict);
//
//
//        } catch (Exception e) {
//
//        }
//
////        return CommonUtils.listRemoveDuplicate(conflictList);
//
//    }
    private static List<CoursePlan> getConflictCourses(List<Lesson> lessons, Method method) {
        List<CoursePlan> conflictList = new ArrayList<>();
        Map<String, List<Lesson>> groupMap = lessons.stream().collect(Collectors.groupingBy(item -> getUniqueCode(item, method)));
        for (String key : groupMap.keySet()) {
            List<Lesson> list = groupMap.get(key);
            if (list.size() > 1) {
                list.stream().forEach(item -> conflictList.add(item.getCoursePlan()));
            }
        }
        return conflictList;
    }

    private static String getUniqueCode(Lesson lesson, Method method) {

        try {
            Condition o = ((Condition) method.invoke(lesson));
            return o.getCode() + Constant.SPLIT_SYMBOL +
                    lesson.getSegment().getWeekDay() + Constant.SPLIT_SYMBOL +
                    lesson.getSegment().getSlot() + Constant.SPLIT_SYMBOL +
                    lesson.getSegment().getCycleType().ordinal();
        } catch (Exception e) {

            return "";
        }
    }





    public static List<? extends  Condition> getFitnessList(List<? extends Condition> conditionList,String fitType){
        return conditionList.stream().filter(m ->m.getFitType() == fitType).collect(Collectors.toList());

    }

    private static void generateWeekSegment(){
        List<Segment> list = new ArrayList<>();
        for (int i = 1; i <= Constant.DAYS_OF_WEEK; i++) {
            for (int j = 1; j <= (Constant.LESSONES_OF_AM+Constant.LESSONES_OF_PM+Constant.LESSONES_OF_EVENING); j++) {
                Segment segment = new Segment(i,j);
                list.add(segment);
            }
        }
        ArrangeUtils.weekSegment = list;
    }
    public static List<Segment> getAllSegment(){
        if(weekSegment==null){
            generateWeekSegment();
        }
        return weekSegment;
    }
}
