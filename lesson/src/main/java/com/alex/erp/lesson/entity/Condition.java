package com.alex.erp.lesson.entity;

import lombok.Data;

/**
 * @version 0.0.1
 * @Description
 * @Author Alex ZY Liang
 * @Date 2019-10-13 3:23 PM
 */

@Data
public abstract class Condition implements Cloneable {
    private String id;
    private String name;
    private String code;
    private String fitType;
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
