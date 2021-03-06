package com.alex.erp.lesson.entity;

import lombok.Data;
import lombok.Generated;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * @version 0.0.1
 * @Description
 * @Author Alex ZY Liang
 * @Date 2019-10-13 1:26 PM
 */
@Data
public class Lesson implements Cloneable,Serializable {

    private String id = UUID.randomUUID().toString();
    private CoursePlan coursePlan;
    private List<Students> students;
    private ClassRoom classRoom;
    private Segment segment;
    private Teacher teacher;
    private List<Teacher> assistant;
    private Enum cycleType;

    private int classRoomType;
    private int segmentType;
    private int teacherType;

    public Lesson(){

    }

    public Lesson(CoursePlan coursePlan, Segment segment, ClassRoom classRoom, Teacher teacher,List<Students> students, Enum cycleType) {
        this.coursePlan = coursePlan;
        this.classRoom = classRoom;
        this.segment = segment;
        this.teacher = teacher;
        this.students = students;
        this.cycleType = cycleType;
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        Lesson lesson = (Lesson) super.clone();
        lesson.segment =(Segment) segment.clone();
        return lesson;
    }


}
