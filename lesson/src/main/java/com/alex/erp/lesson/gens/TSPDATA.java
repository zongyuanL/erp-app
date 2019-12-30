package com.alex.erp.lesson.gens;

/**
 * @version 0.0.1
 * @Description
 * @Author Alex ZY Liang
 * @Date 2019-12-18 3:31 PM
 */
public class TSPDATA {

    public static  int SPECIES_Num = 200; //种群数
    public static  int Develop_Num = 1000; //进化代数
    public static  float pcl = 0.7f, pch = 0.95f;//交叉概率
    public static  float pm = 0.4f; //变异概率
    public static  String[] Course= { "00150","00230","00340","00450","00560","00660","00760" };//课程集合
    public static  String[] Room= { "00150", "00230", "00340", "00450", "00560", "00660", "00760" }; //教室集合
    public static  String[] Classm= {"001","002","003","004","005","006" }; //班级集合
    public static  String[] Teacher= {"001","002","003","004" }; //教师集合
    public static  String[] Time ={"11","12","13","14","21","22","31","41","43","51","52" }; //时间集合
    public static  int LessonNum = Room.length * Time.length;
}
