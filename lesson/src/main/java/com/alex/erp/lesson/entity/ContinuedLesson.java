package com.alex.erp.lesson.entity;

import java.util.List;

/**
 * @version 0.0.1
 * @Description
 * @Author Alex ZY Liang
 * @Date 2019-12-26 11:01 AM
 */
public class ContinuedLesson extends Lesson {

    private ContinuedLesson preLesson;
    private ContinuedLesson nextLesson;

    public ContinuedLesson(CoursePlan coursePlan, Segment segment, ClassRoom classRoom, Teacher teacher, List<Students> students, Enum cycleType) {
        super(coursePlan, segment, classRoom, teacher, students, cycleType);
    }

    public void setPreLesson(ContinuedLesson preLesson) {
        preLesson.setNextLesson(this);
        this.preLesson = preLesson;
    }

    public void setNextLesson(ContinuedLesson nextLesson){
        this.nextLesson = nextLesson;
        nextLesson.setPreLesson(this);
    }
}
