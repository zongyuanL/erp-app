package com.alex.erp.lesson.entity;

import com.alex.erp.lesson.dic.Constant;
import com.alex.erp.lesson.dic.CourseEnum;
import lombok.Data;

import java.util.List;

/**
 * @version 0.0.1
 * @Description
 * @Author Alex ZY Liang
 * @Date 2019-10-12 5:36 PM
 */
@Data
public class Segment extends Condition{
    private int weekDay;
    private int slot;
    private int state;
    private Enum cycleType = CourseEnum.CYCLE_TYPE_EVERY;
    private List<CoursePlan> fitnessCoursePlan;

    @Override
    public boolean equals(Object s){
        Segment segment = (Segment) s;
        return (segment.getWeekDay()==this.getWeekDay() & segment.getSlot()==this.getSlot());
    }

    public Segment(int weekDay, int slot) {
        this.weekDay = weekDay;
        this.slot = slot;
    }

    @Override
    public String toString(){
        return this.getWeekDay() + Constant.JOIN_STRING + this.getSlot();
    }
}
