package com.ems902.Service;

import com.ems902.Entity.Lesson;

import java.util.List;

public interface LessonService {
    //查询所有lesson
    public List<Lesson> queryAllLesson();
    //添加lesson内容
    public int addNewLesson(Lesson lesson);
    //查询指定学院的lesson
    public List<Lesson> queryLessonBySchool(String lessonSchool);
}
