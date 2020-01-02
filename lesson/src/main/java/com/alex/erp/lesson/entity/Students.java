package com.alex.erp.lesson.entity;

import com.alex.erp.lesson.dic.Constant;
import lombok.Data;

/**
 * @version 0.0.1
 * @Description
 * @Author Alex ZY Liang
 * @Date 2019-12-24 9:31 AM
 */

@Data
public class Students extends EducationResource implements Cloneable{

    private String classID;
    private String specialityField;


    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o){
        Students s = (Students) o;
        return s.getClassID().equals(this.classID) && (s.getSpecialityField().equals(this.specialityField) ||
            s.getSpecialityField().equals(Constant.ALL_SPECIALITYFIELD) ||
            this.getSpecialityField().equals(Constant.ALL_SPECIALITYFIELD));
    }

    @Override
    public String toString(){
        return this.getClassID()+Constant.JOIN_STRING+this.getSpecialityField();
    }
}
