package com.alex.erp.lesson.entity;

import lombok.Data;

import java.util.List;

/**
 * @version 0.0.1
 * @Description
 * @Author Alex ZY Liang
 * @Date 2019-10-12 5:21 PM
 */
@Data
public class ClassRoom extends EducationResource {

    private String shortName;
    private String address;
    private int capacity;
    private boolean state;
    private List<CoursePlan> fitnessCours;


}
