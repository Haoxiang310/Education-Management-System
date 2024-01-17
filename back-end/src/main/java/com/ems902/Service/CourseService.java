package com.ems902.Service;

import com.ems902.Entity.Classroom;
import com.ems902.Entity.Course;

import java.util.List;

public interface CourseService {
    //课程进行排课
    public Classroom SetClassroom(Course course);
    //更新课程信息
    public void updateCourseInfo(Course course, Classroom classroom);
    //查询课程信息
    public Course queryCourseInfoById(int cId);
    //通过课程名查找讲台
    public List<Course> queryCourseInfoByName(String cName);



}
