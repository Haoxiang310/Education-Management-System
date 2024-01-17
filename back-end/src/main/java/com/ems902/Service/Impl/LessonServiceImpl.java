package com.ems902.Service.Impl;

import com.ems902.Dao.UserDao;
import com.ems902.Entity.Lesson;
import com.ems902.Service.LessonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;
import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {

    @Resource
    private UserDao userDao;
    @Override
    public List<Lesson> queryAllLesson() {
        return userDao.selectAllLesson();
    }

    @Override
    public int addNewLesson(Lesson lesson) {
        return userDao.addLesson(lesson);
    }

    @Override
    public List<Lesson> queryLessonBySchool(String lessonSchool) {
        return userDao.selectLessonBySchool(lessonSchool);
    }
}
