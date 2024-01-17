package com.ems902.Service.Impl;


import com.ems902.Dao.UserDao;
import com.ems902.Entity.Classroom;
import com.ems902.Entity.Course;
import com.ems902.Service.ClassroomService;
import com.ems902.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class CourseServiceImpl implements CourseService {
    @Resource
    private UserDao userDao;
    @Resource
    private ClassroomService classroomService;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    public void setClassroomService(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }


    @Override
    public Classroom SetClassroom(Course course) {
        //List<Classroom> selectedClassroom = new ArrayList<>();

        //找单周的教室，先进行测试
        //List<Classroom> classrooms = classroomService.getEmptyClassroom(course.getStartweek(),course.getDay(),course.getDay());
        //没有找到对应条件的教室，返回null
//        if(classrooms.size() == 0) return null;
//        else {
//            Classroom classroom = classrooms.get(0);
//            //更新三个表的信息
//            updateCourseInfo(course,classroom);
//            //返回classroom值
//            return classroom;
//        }

        /*for(int week = course.getStartweek(); week <= course.getEndweek(); week++){
            List<Classroom> classrooms = classroomService.getEmptyClassroom(week,course.getDay(),course.getDay());
            if(classrooms.size() == 0) return null;
            else {
                Classroom classroom = classrooms.get(0);
                //更新classroom表信息
                classroomService.updateClassroomInfo(classroom);
                //更新course-classroom表信息
                classroomService.setClassroomCourseInfo(classroom,course);
                //更新course表信息
                userDao.updateCourseIntoCourse(course);
            }
        }*/
        return null;
    }

    @Override
    public void updateCourseInfo(Course course, Classroom classroom) {
        //更新classroom表的信息
        classroomService.updateClassroomInfo(classroom);
        //更新course-classroom表信息
        classroomService.setClassroomCourseInfo(classroom,course);
        //更新course表信息
        userDao.updateCourseIntoCourse(course);
    }

    @Override
    public Course queryCourseInfoById(int cId) {
        return userDao.selectCourseInfoById(cId);
    }

    @Override
    public List<Course> queryCourseInfoByName(String cName) {
        return userDao.getCourseByName(cName);
    }
}
