package com.ems902.Service.Impl;


import com.ems902.Dao.UserDao;
import com.ems902.Entity.Classroom;
import com.ems902.Entity.Course;
import com.ems902.Entity.CourseClassroom;
import com.ems902.Service.ClassroomService;
import jdk.nashorn.internal.runtime.linker.LinkerCallSite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClassroomServiceImpl implements ClassroomService {

    @Resource
    private UserDao userDao;


    @Override
    public List<Classroom> getEmptyClassroom(int week, int day, int time, String place) {
        Map<Object,Object> map = new HashMap<>();
        map.put("week",week);
        map.put("day",day);
        map.put("time",time);
        map.put("place",place);

        List<Classroom> classrooms = userDao.selectClassroomByMap(map);
        return classrooms;

    }

    @Override
    public void setClassroomCourseInfo(Classroom classroom, Course course) {
        CourseClassroom courseClassroom = new CourseClassroom(classroom.getRoomId(),course.getcId());
        userDao.addCourseAndRoom(courseClassroom);
    }

    @Override
    public void updateClassroomInfo(Classroom classroom) {
        userDao.updateClassroomInfo(classroom);
    }

    @Override
    public List<String> judgeEmptyClassroom(String place, int startweek, int endweek, int day, int time) {

        List<Integer> clashWeekList = new ArrayList<>();
        List<String> clashClassroomInfoStringList = new ArrayList<>();

        for(int thisWeek = startweek;thisWeek <= endweek;thisWeek++){
            List<Classroom> classrooms = getEmptyClassroom(thisWeek,day,time,place);
            StringBuilder classroomCrashInfo = new StringBuilder("Week " + thisWeek);

            //如果有冲突，返回具体的信息
            if(classrooms.size() > 0){
                for (Classroom classroom : classrooms) {
                    if(classroom.getPlace().equals(place))
                        classroomCrashInfo.append(";place ").append(place);
                    if(classroom.getDay() == day){
                        classroomCrashInfo.append(";day ").append(day);
                    }
                    if(classroom.getTime() == time){
                        classroomCrashInfo.append(";time ").append(time);
                    }
                }
            }
            //没有冲突返回没有冲突的信息
            else {
                classroomCrashInfo.append("no crash");
            }
            clashClassroomInfoStringList.add(classroomCrashInfo.toString());
        }
        return clashClassroomInfoStringList;

    }

    @Override
    public int setClassroom(int cId, String place, int startweek, int endweek, int day, int time) {

        //需要删除之前的课程占的教室
        List<CourseClassroom> courseClassroomList =  userDao.getRoomIdByCourseId(cId);
        for (CourseClassroom courseClassroom : courseClassroomList) {
            Classroom classroom = userDao.selectClassroomById(courseClassroom.getRoomId());
            userDao.deleteClassroom(classroom);
            userDao.deleteCourseAndRoom(courseClassroom);
        }

        int res = 0;
        for(int thisWeek = startweek;thisWeek <= endweek;thisWeek++){
            Classroom classroom = new Classroom(place,thisWeek,day,time,1);
            res += userDao.addClassroom(classroom);
            Map<Object,Object> map1 = new HashMap<>();
            map1.put("place",place);
            map1.put("week",thisWeek);
            map1.put("day",day);
            map1.put("time",time);
            Classroom classroom1 = userDao.selectClassroomInfoByMap(map1);
            res += userDao.addCourseAndRoom(new CourseClassroom(classroom1.getRoomId(),cId));
        }

        Map<Object,Object> map = new HashMap<>();
        map.put("cId",cId);
        map.put("place",place);
        map.put("startweek",startweek);
        map.put("endweek",endweek);
        map.put("day",day);
        map.put("time",time);
        res += userDao.updateCourseInfoByMap(map);
        if(res == (endweek-startweek+1)*2+1)
            return 1;
        else return 0;
    }
}

