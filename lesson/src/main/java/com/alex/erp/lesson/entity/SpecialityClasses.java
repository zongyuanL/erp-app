package com.alex.erp.lesson.entity;

import lombok.Data;

/**
 * @version 0.0.1
 * @Description
 * @Author Alex ZY Liang
 * @Date 2019-10-12 5:43 PM
 */
@Data
public class SpecialityClasses {
    private String id;
    private String name;
    private String code;
    private ClassRoom classRoom;
    private String  specialityName;
    private String  specialitycode;

}
