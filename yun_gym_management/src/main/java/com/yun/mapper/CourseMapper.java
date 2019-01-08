package com.yun.mapper;

import com.yun.pojo.Course;

public interface CourseMapper {

    void delCourseByGymArr(Course course);

    void courseadd(Course course);

    Course courfind(Course course);

    Course coursfindMachine(Course course);

}
