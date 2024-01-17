package com.ems902.Service.Impl;

import com.ems902.Dao.CourseDao;
import com.ems902.Dao.UserDao;
import com.ems902.Entity.Admin;
import com.ems902.Entity.Course;
import com.ems902.Entity.ScoreCheck;
import com.ems902.Entity.StuCourse;
import com.ems902.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<StuCourse> getAllCourseScoreByName(String cName) {
        List<Course> courseList = userDao.getCourseByName(cName);
        //List<Integer> selectedCourseIdList = userDao.selectCourseIdByName(cName);
        List<StuCourse> selectedStuCourseList = new ArrayList<>();
        for (Course course : courseList) {
            List<StuCourse> stuCourseList  = userDao.selectStuCourseByCourseId(course.getcId());
            for (StuCourse stuCourse : stuCourseList) {
                selectedStuCourseList.add(stuCourse);
            }
        }
        return selectedStuCourseList;
    }

    @Override
    public List<StuCourse> getSingleCourseScoreById(int cId) {
        return userDao.selectStuCourseByCourseId(cId);
    }

    @Override
    public List<StuCourse> getStuAllCourse(int sId) {
        return userDao.selectCourseIdByStuId(sId);
    }

    @Override
    public int updateAdminCheck(int checkId, int adminCheck) {
        ScoreCheck scoreCheck = userDao.selectScoreCheckById(checkId);
        scoreCheck.setAdminCheck(adminCheck);
        return userDao.updateAdminCheck(scoreCheck);
    }

    @Override
    public List<Admin> getAdminById(int adminId) {
        return userDao.selectAdminsByAdminId(adminId);
    }

    @Override
    public List<Course> getCourseByMajor(String major) {
        return userDao.getCourseByMajor(major);
    }
}
