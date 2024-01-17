package com.ems902.Service;

import com.ems902.Entity.Classroom;
import com.ems902.Entity.Course;

import java.util.List;

public interface ClassroomService {
    //根据条件判断是否有空余教室，若有则返回空教室列表，否则返回null
    public List<Classroom> getEmptyClassroom(int week, int day, int time, String place);

    //选择教室后，将教室表、教室-课程表信息更新
    public void setClassroomCourseInfo(Classroom classroom, Course Course);

    //更新classroom表信息
    public void updateClassroomInfo(Classroom classroom);

    //判断教室是否为空，根据week,day,time
    public List<String> judgeEmptyClassroom(String place, int startweek, int endweek, int day, int time);

    //设置教室
    public int setClassroom(int cId, String place, int startweek, int endweek, int day, int time);


}
