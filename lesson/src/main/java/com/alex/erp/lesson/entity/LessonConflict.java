package com.alex.erp.lesson.entity;

import lombok.Data;

import java.util.List;

/**
 * @version 0.0.1
 * @Description
 * @Author Alex ZY Liang
 * @Date 2020-01-02 2:39 PM
 */
@Data
public class LessonConflict {
    private List<EducationResource> conflictResources;
    private Segment conflictSegment;
    private String conflictDetail;
}
