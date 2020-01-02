package com.alex.erp.lesson.dic;

import com.alex.erp.lesson.entity.Segment;

import java.util.List;

/**
 * @version 0.0.1
 * @Description
 * @Author Alex ZY Liang
 * @Date 2019-10-13 1:23 PM
 */
public class Constant {

    public static  int WEIGHT_OF_CLASSROOM = 1;
    public static  int WEIGHT_OF_SEGMENT= 1;
    public static  int WEIGHT_OF_TEACHER = 1;


    public static int TRY_TIMES_OF_ARRANGE = 20;

    public static String SPLIT_SYMBOL ="||";
    public static String JOIN_STRING = "||";

    public static int DAYS_OF_WEEK = 5;
    public static int LESSONES_OF_AM = 4;
    public static int LESSONES_OF_PM = 4;
    public static int LESSONES_OF_EVENING = 2;

    public static int MAX_SOLT = 10;

    public static String CAN_NOT_ARRANGE_LESSON="条件冲突，无法排课，请调整相关限制后再重新排课";

    public static String GENERATE_TYPE_SPEED = "SPEED";
    public static String GENERATE_TYPE_BEST = "BEST";

    public static String ALL_SPECIALITYFIELD = "ALL_SPECIALITYFIELD";

    public static String TEACHER_TYPE_TEACHER="TEACHER";
    public static String TEACHER_TYPE_ASSISTANT="ASSISTANT";
    public static String TEACHER_TYPE_BOTH="BOTH";




//    public static int MAX_CLASSROOM = 200;
//    public static int MAX_SEGMENT = 50;
//    public static int MAX_TEACHER = 10;

//    public static List<Segment>
}
