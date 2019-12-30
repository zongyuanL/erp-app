package com.alex.erp.lesson.entity;

import com.alex.erp.lesson.ArrangeUtils;
import com.alex.erp.lesson.dic.CourseEnum;
import com.alex.erp.lesson.utils.CommonUtils;
import lombok.Data;

import java.util.List;

/**
 * @version 0.0.1
 * @Description
 * @Author Alex ZY Liang
 * @Date 2019-10-23 5:18 PM
 */

@Data
public class EducationResource extends Condition implements Cloneable{

    private List<Segment> fitnessSegment;

    public List<Segment> getAvaliableSegment(){
        List<? extends Condition> requiredList = ArrangeUtils.getFitnessList(this.fitnessSegment,CourseEnum.REQUIRED.getCode());
        if(requiredList.size()==0){
            requiredList = ArrangeUtils.getAllSegment();
        }
        return CommonUtils.getDifferenceList(requiredList,ArrangeUtils.getFitnessList(this.fitnessSegment,CourseEnum.DISREQUIRED.getCode()));

    }

}
