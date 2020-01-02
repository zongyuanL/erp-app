package com.alex.erp.lesson.dic;

/** 
 *
 * @Description 
 * @param null
 * @return  
 * @Throw 
 * @Author Alex ZY Liang
 * @Date 2020/1/2 2:30 PM
 * @Version 0.0.1
 **/
public enum MutationAndCrossPositionEnum {

    /** 
     *
     * weekday
     *
     **/
    WEEKDAY(1),

    /**
     *
     * slot
     *
     **/
    SLOT(2),

    /**
     *
     * teacher
     *
     **/
    TEACHER(3),

    /**
     *
     * assistant
     *
     **/
    ASSISTANT(4),

    /**
     *
     * classroom
     *
     **/
    CLASSROOM(5);





    private final int code;

    MutationAndCrossPositionEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }


    public static MutationAndCrossPositionEnum getByValue(int code) {
        for (MutationAndCrossPositionEnum codes : values()) {
            if (codes.getCode() == code) {
                return codes;
            }
        }
        return null;
    }

}
