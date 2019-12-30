package com.alex.erp.lesson.dic;

public enum CourseEnum {

    CYCLE_TYPE_ODD("ODD","单周"),

    CYCLE_TYPE_EVEN("EVEN","双周"),

    CYCLE_TYPE_EVERY("EVERY","每周"),

    //可用
    AVALIABLE("AVALIABLE","可用"),

    //不可用
    UNAVALIABLE("UNAVALIABLE","不可用"),


    REQUIRED("REQUIRED","必须"),

    DISREQUIRED("DISREQUIRED","不可使用"),


    NATIVE_CLASSROOM("NATIVE_CLASSROOM","本班教室"),

    //优先，不强制要求
    PRIORITY("HIGH_PRIORITY","优先使用");





    private String code;

    private String message;

    CourseEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public String getMessage(String message) {
        return String.format(this.message, message == null ? "" : message);
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
