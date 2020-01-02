package com.alex.erp.lesson.entity;

import lombok.Data;

import java.util.List;

/**
 * @version 0.0.1
 * @Description
 * @Author Alex ZY Liang
 * @Date 2019-10-12 5:22 PM
 */
@Data
public class Teacher  extends EducationResource{
    private String name;
    private String code;
    private String type;
    private List<CoursePlan> teachingCoursePlan;

    private List<SpecialityClasses> teachingClass;



    @Override
    public boolean equals(Object o){
        Teacher t = (Teacher)o;
        return this.getId()==((Teacher) o).getId();
    }
}
