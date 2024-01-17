package com.ems902.Service;

import com.ems902.Entity.*;

import java.util.List;

public interface UserService {
    public int checkAccount(int id, int kind, String pwd);
    public String getStuNameById(int id);
    public String getTeaNameById(int id);
    public List<Course> checkCourseTable(int sId);
    public List<StuCourse> checkStuCourses(int sId);
    public Teacher selectTeacherByCourseId(int cId);
    public int checkGrade(int cId, int sId);
    public List<Course> queryAllCourses();
    public int chooseClass(int sId,int cId);
    public int cancelClass(int sId,int cId);
    public List<Course> checkTeaCourseTable(int tId);
    public List<Student> checkCourseStu(int cId);
    public List<StuCourse> queryStuScore(int sId,int cId);
    public int updateGrade(int sId, int cId, int usual_grade, int final_grade);
    public int ScoreCheck(int sId,int tId,int cId,int old_score,int new_score,int old_usual_score,
                          int old_final_score, int new_usual_score,int new_final_score,String changeReason);
    public List<ScoreCheck> TeaScoreCheck(int cId, int tId);
    public List<ScoreCheck> getAllScoreCheck(int adminId);



    public int updateCourseRequirement(Course course);
    public int insertCourseChange(CourseChange courseChange);
    public int deleteCourseChange(int changeId);
    public List<CourseChange> queryCourseChange(int tId);
    public int queryIfChosen(int sId,int cId);
    public List<ScoreCheck> queryAllScoreCheck(int tId);
    //通过审批状态选择停调课表
    public List<CourseChange> queryCourseChangeByStatus(int status ,int adminId);
    //根据停调课表id更改状态
    public int changeCourseChangeStatus(int changeId,int status);
    //获取所有学生信息
    public List<Student> getAllStuInfo(int adminId);
    public String querycNameBycId(int cId);
}
