package com.alex.erp.lesson.entity;

import lombok.Data;

import java.util.List;

/**
 * @version 0.0.1
 * @Description
 * @Author Alex ZY Liang
 * @Date 2019-12-25 10:44 AM
 */

@Data
public class Course {

    private String id;
    private String name;
    private String alias;
    private String code;
    private List<Teacher> fittedTeachers;
    private List<ClassRoom> fittedClassRooms;
    private List<Segment> fittedSegments;

}
