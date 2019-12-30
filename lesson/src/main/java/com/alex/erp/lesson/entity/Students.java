package com.alex.erp.lesson.entity;

import lombok.Data;

/**
 * @version 0.0.1
 * @Description
 * @Author Alex ZY Liang
 * @Date 2019-12-24 9:31 AM
 */

@Data
public class Students implements Cloneable{

    private String classID;
    private String specialityField;


    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
