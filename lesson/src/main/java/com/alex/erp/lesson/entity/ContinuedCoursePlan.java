package com.alex.erp.lesson.entity;

import com.alex.erp.lesson.utils.CommonUtils;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 0.0.1
 * @Description
 * @Author Alex ZY Liang
 * @Date 2019-10-23 3:15 PM
 */
@Data
public class ContinuedCoursePlan extends CoursePlan {
    private List<CoursePlan> continuedCoursePlans;

    @Override
    public List<Lesson> generateLesson() {
        List<Lesson> list = new ArrayList<>();
        List<Segment> segments = new ArrayList<>();
        ContinuedLesson[][] lessonArray =  new ContinuedLesson[quantity][continuedCoursePlans.size()];

        for (int i = 0; i < quantity; i++) {
            Segment segment = generateSegments();
            segments.add(segment);
        }

        for (int j = 0; j < continuedCoursePlans.size(); j++) {
            CoursePlan plan = continuedCoursePlans.get(j);
            Teacher teacher = plan.generateTeacher();
            ClassRoom classRoom = plan.generateClassroom();
            for (int i = 0; i < quantity; i++) {
                Segment segment = segments.get(i);
                segment.setSlot(segment.getSlot()+i);
                ContinuedLesson lesson = new ContinuedLesson(plan,segment,classRoom,teacher,students,cycleType);
                lessonArray[i][j] = lesson;
            }
        }

        for (int i = 0; i < quantity; i++) {
            ContinuedLesson[] lessons = lessonArray[i];
            for (int j = 0; j < continuedCoursePlans.size()-1; j++) {
                ContinuedLesson lesson = lessons[j];
                lesson.setNextLesson(lessons[j+1]);
                list.add(lesson);
            }
        }
        return list;
    }

//    private Segment generateSegments(){
//
//        Segment segment;
//        Random random = new Random();
//        List<Segment> availableResources;
//        if(this.getAvailableSegments().size()>0){
//            availableResources =this.getAvailableSegments();
//        }else{
//            availableResources = this.getCourse().getFittedSegments();
//        }
//        int n = random.nextInt(availableResources.size());
//        segment = availableResources.get(n);
//        return segment;
//
//    }


    @Override
    public void calculatePriority() {
        super.calculatePriority();
        this.priority = this.priority / 2;

    }


    @Override
    public void calculateAvailableSegment() {
        List<Segment> segmentList = null;
        for (int i = 0; i < this.getContinuedCoursePlans().size(); i++) {
            CoursePlan plan = continuedCoursePlans.get(i);
            List<Segment> _segmentList  =  plan.getAvailableSegments();
            if(null == segmentList){
                segmentList = _segmentList;
                continue;
            }
            int index = i;
            _segmentList.stream().forEach(o->o.setSlot(o.getSlot()-index));
            segmentList = CommonUtils.getIntersectedList(segmentList,_segmentList);
            if(segmentList.size()==0){
                break;
            }
        }

        this.setAvailableSegments(segmentList);
//
//        this.secondCoursePlan.calculateAvailableSegment();
//        List<Segment> secondCourseAvaliableSegments = this.secondCoursePlan.getAvailableSegments();
//        this.availableSegments = this.availableSegments.stream().filter(
//            item -> !(
//                    item.getSlot()== (Constant.LESSONES_OF_AM-1) ||
//                    item.getSlot()== (Constant.LESSONES_OF_AM + Constant.LESSONES_OF_PM - 1) ||
//                    item.getSlot()== (Constant.LESSONES_OF_AM + Constant.LESSONES_OF_PM + Constant.LESSONES_OF_EVENING- 1)
//        ) && secondCourseAvaliableSegments.contains(new Segment(item.getWeekDay(),item.getSlot()-1)) ).collect(Collectors.toList());

    }


}
