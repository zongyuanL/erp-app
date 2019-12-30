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

    public static List<CoursePlan> getConflictCourse(List<Lesson> lessons) {

        List<CoursePlan> conflictList = new ArrayList<>();

        try {

            Class clz = Lesson.class;

            //同一个教师在同一个时间有两节课
            Method method = clz.getMethod("getTeacher");
            List<CoursePlan> teacherConflict = getConflictCourses(lessons, method);

            //同一个教室在同一个时间有两节课
            method = clz.getMethod("getClassRoom");
            List<CoursePlan> classRoomConflict = getConflictCourses(lessons, method);

            //同一个专业方向在同一个时间有两节课
            method = clz.getMethod("getSpecialityClasses");

            List<CoursePlan> classConflict = getConflictCourses(lessons, method);

            conflictList.addAll(classRoomConflict);
            conflictList.addAll(teacherConflict);
            conflictList.addAll(classConflict);


        } catch (Exception e) {

        }

        return CommonUtils.listRemoveDuplicate(conflictList);
    }

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
