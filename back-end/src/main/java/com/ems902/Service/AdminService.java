package com.ems902.Service;

import com.ems902.Entity.Admin;
import com.ems902.Entity.Course;
import com.ems902.Entity.StuCourse;

import java.util.List;

public interface AdminService {
    //通过课程名获取所有讲台的成绩
    public List<StuCourse> getAllCourseScoreByName(String cName);

    //通过课程id获取讲台成绩
    public List<StuCourse> getSingleCourseScoreById(int cId);

    //通过学生id获取该学生所有课程，并生成成绩单
    public List<StuCourse> getStuAllCourse(int sId);

    //更新scorecheck审批状态
    public int updateAdminCheck(int checkId, int adminCheck);

    public List<Admin> getAdminById(int adminId);

    public List<Course> getCourseByMajor(String major);




}
