package com.ems902.Service.Impl;

import com.ems902.Dao.CourseDao;
import com.ems902.Dao.UserDao;
import com.ems902.Entity.*;
import com.ems902.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public int checkAccount(int id, int kind, String pwd) {
        if (kind == 1) {
            if (userDao.selectStuById(id).getsPwd().equals(pwd))
                return 1;
        }
        else if (kind == 2) {
            if (userDao.selectTeaById(id).gettPwd().equals(pwd))
                return 2;
        }
        else if(kind==3){
            if(userDao.selectAdminById(id).getAdminPwd().equals(pwd))
                return 3;
        }
        return 0;
    }

    @Override
    public String querycNameBycId(int cId) {
        return userDao.querycNameBycId(cId);
    }

    @Override
    public List<Course> checkCourseTable(int sId) {
        List<Course> courses = new ArrayList<>();
        List<StuCourse> stuCourselist = userDao.selectCourseIdByStuId(sId);
        for(StuCourse i:stuCourselist){
            courses.add(userDao.selectCourseInfoById(i.getcId()));
        }
        return courses;
    }

    @Override
    public List<StuCourse> checkStuCourses(int sId) {
        List<StuCourse> stuCourses = userDao.selectCourseIdByStuId(sId);
        return stuCourses;
    }

    @Override
    public List<Course> queryAllCourses() {
        List<Course> courses = userDao.queryAllCourses();
        return courses;
    }

    @Override
    public int queryIfChosen(int sId, int cId) {
        Map map = new HashMap();
        map.put("sId",sId);
        map.put("cId",cId);
        System.out.println((userDao.queryIfChosen(map) == null));
        if(userDao.queryIfChosen(map)==null) return 0;
        else return 1;
    }

    @Override
    public int checkGrade(int cId, int sId) {
        Map map = new HashMap();
        map.put("cId",cId);
        map.put("sId",sId);
        int grade = userDao.checkGrade(map);
        return grade;

    }

    @Override
    public Teacher selectTeacherByCourseId(int cId) {

        TeaCourse teaCourse = userDao.selectTeaIdByCourseId(cId);
        Teacher teacher = userDao.selectTeaById(teaCourse.gettId());
        return teacher;
    }

    @Override
    public List<ScoreCheck> queryAllScoreCheck(int tId) {
        List<ScoreCheck> scoreChecks = userDao.queryAllScoreCheck(tId);
        return scoreChecks;
    }

    @Override
    public int chooseClass(int sId, int cId) {
        StuCourse stuCourse = new StuCourse(sId,cId);
        List<StuCourse> stuCourses = userDao.selectCourseIdByStuId(sId);
        List<Course> courses = new ArrayList<>();
        Student student = userDao.selectStuById(sId);
        for(StuCourse i:stuCourses){
            courses.add(userDao.selectCourseInfoById(i.getcId()));
        }
        Course this_course = userDao.selectCourseInfoById(cId);
        for(Course j:courses){
            if(j.getDay()==this_course.getDay()&&
                    j.getTime()==this_course.getTime()&&
                    j.getStartweek()==this_course.getStartweek()&&
                    j.getEndweek()==this_course.getEndweek()){
                return 0; //时间发生冲突
            }
        }

        if(this_course.getMajor().equals("all")&&this_course.getGrade()==0)
        {userDao.addCourse(stuCourse);
            return 1;}
        else if((student.getGrade()==this_course.getGrade())&&(student.getMajor().equals(this_course.getMajor()))==true){
            userDao.addCourse(stuCourse);
            return 1;
        }
        else if(student.getGrade() != this_course.getGrade()) {
            if ((student.getMajor().equals(this_course.getMajor())) == true)  return 2; //年级不符合要求

            else if ((student.getMajor().equals(this_course.getMajor())) == false)  return 4; //年级和专业都不符合要求
        }
        else if ((student.getGrade()==this_course.getGrade())&&(student.getMajor().equals(this_course.getMajor()))==false) return 3; //专业不符合要求
        return 5;
    }

    @Override
    public int cancelClass(int sId, int cId) {
        StuCourse stuCourse = new StuCourse(sId,cId);
        userDao.deleteCourse(stuCourse);
        return 1;
    }

    @Override
    public List<Course> checkTeaCourseTable(int tId) {
        List<Course> courses = new ArrayList<>();
        List<TeaCourse> teaCourselist = userDao.selectCourseIdByTeaId(tId);
        for(TeaCourse i:teaCourselist){
            courses.add(userDao.selectCourseInfoById(i.getcId()));
        }
        return courses;
    }

    @Override
    public List<Student> checkCourseStu(int cId) {
        List<Student> stu = new ArrayList<>();
        List<StuCourse> stuCourses = userDao.selectStuIdByCourseId(cId);
        for(StuCourse i:stuCourses){
            stu.add(userDao.selectStuById(i.getsId()));
        }
        return stu;
    }

    @Override
    public String getStuNameById(int id) {
        return userDao.selectStuById(id).getName();
    }

    @Override
    public String getTeaNameById(int id) {
        return userDao.selectTeaById(id).gettName();
    }

    @Override
    public List<StuCourse> queryStuScore(int sId, int cId) {
        StuCourse stuCourse = new StuCourse(sId,cId);
        List<StuCourse> stuCourses = userDao.queryStuScore(stuCourse);

        return stuCourses;
    }

    @Override
    public int updateGrade(int sId, int cId, int usual_grade, int final_grade) {
        StuCourse stuCourse = new StuCourse(sId, cId, usual_grade+final_grade,usual_grade,final_grade);
        userDao.updateGrade(stuCourse);
        return 1;
    }

    @Override
    public int updateCourseRequirement(Course course) {
        userDao.updateCourseIntoCourse(course);
        return 1;
    }

    @Override
    public int ScoreCheck(int sId, int tId, int cId, int old_score, int new_score,int old_usual_score,int old_final_score,
                          int new_usual_score,  int new_final_score, String changeReason) {
        ScoreCheck scoreCheck = new ScoreCheck(sId,tId,cId,old_score,new_score,old_usual_score,
                old_final_score,new_usual_score,new_final_score,changeReason,0);
        userDao.ScoreCheck(scoreCheck);
//        StuCourse stuCourse = new StuCourse(cId,sId,new_score);
//        userDao.updateGrade(stuCourse);
        return 1;
    }

    @Override
    public List<ScoreCheck> TeaScoreCheck(int cId, int tId) {
        List<ScoreCheck> scoreCheck = new ArrayList<>();
        Map map = new HashMap();
        map.put("cId",cId);
        map.put("tId",tId);
        scoreCheck = userDao.TeaScoreCheck(map);
        return scoreCheck;
    }

    @Override
    public List<ScoreCheck> getAllScoreCheck(int adminId) {
        Admin admin = userDao.selectAdminById(adminId);
        List<Teacher> teacherList = userDao.queryTeaByIns(admin.getAdminSchool());
        List<ScoreCheck> scoreCheckList = new ArrayList<>();
        if(admin.getAdminSchool().equals("school"))//学校教务
            return userDao.getAllScoreCheck();
        else {
            for (Teacher teacher : teacherList) {
                scoreCheckList.addAll(userDao.queryAllScoreCheck(teacher.gettId()));
            }
            return scoreCheckList;
        }
    }

    @Override
    public int insertCourseChange(CourseChange courseChange) {
        userDao.insertCourseChange(courseChange);
        return 1;
    }

    @Override
    public int deleteCourseChange(int changeId) {
        userDao.deleteCourseChange(changeId);
        return 1;
    }

    @Override
    public List<CourseChange> queryCourseChange(int tId) {
        return userDao.queryCourseChange(tId);
    }

    @Override
    public List<CourseChange> queryCourseChangeByStatus(int status, int adminId) {
        List<CourseChange> courseChanges = new ArrayList<>();
        Admin admin = userDao.selectAdminById(adminId);
        List<Teacher> teacherList = userDao.queryTeaByIns(admin.getAdminSchool());
        if(admin.getAdminSchool().equals("school"))//学校教务返回所有coursechange
            return userDao.queryCourseChangeByStatus(status);
        else {//返回某个学院的coursechange
            for (Teacher teacher : teacherList) {
                Map<Object,Object> map = new HashMap<>();
                map.put("tId",teacher.gettId());
                map.put("status",status);
                courseChanges.addAll(userDao.querySchoolCourseChangeByStatus(map));
                //courseChanges.addAll(userDao.queryCourseChangeByTeaId(teacher.gettId()));
            }
            return courseChanges;
        }
    }

    @Override
    public int changeCourseChangeStatus(int changeId,int status) {
        Map<Object,Object> map = new HashMap<>();
        map.put("changeId",changeId);
        map.put("status",status);
        return userDao.updateCourseChangeStatus(map);
    }

    @Override
    public List<Student> getAllStuInfo(int adminId) {
        Admin admin = userDao.selectAdminById(adminId);
        if(admin.getAdminSchool().equals("school"))//学校教务
            return userDao.queryAllStudent();
        else {//学院教务
            List<Student>  studentList = userDao.queryStudentBySSchool(admin.getAdminSchool());
            return userDao.queryStudentBySSchool(admin.getAdminSchool());
        }
    }
}
