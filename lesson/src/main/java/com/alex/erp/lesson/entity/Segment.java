package com.alex.erp.lesson.entity;

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

    public boolean equals(Segment s){
       return (s.getWeekDay()==this.getWeekDay() & s.getSlot()==this.getSlot());
    }

    public Segment(int weekDay, int slot) {
        this.weekDay = weekDay;
        this.slot = slot;
    }
}
