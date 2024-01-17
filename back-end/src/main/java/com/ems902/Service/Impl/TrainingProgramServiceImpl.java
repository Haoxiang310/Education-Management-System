package com.ems902.Service.Impl;

import com.alibaba.fastjson.JSON;
import com.ems902.Dao.UserDao;
import com.ems902.Entity.Lesson;
import com.ems902.Entity.TrainingProgram;
import com.ems902.Service.TrainingProgramService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TrainingProgramServiceImpl implements TrainingProgramService {
    @Resource
    //private TrainingProgramDao trainingProgramDao;
    private UserDao userDao;

    @Override
    public int setTrainingProgram(TrainingProgram trainingProgram) {
        return userDao.setTrainingProgram(trainingProgram);
    }

    @Override
    public int updateTrainingProgramCourse(String lessonString,String major)  {
        Map<Object,Object> map = new HashMap<>();
        map.put("lessonList",lessonString);
        map.put("major",major);
        return userDao.updateTrainingProgram(map);
    }

    @Override
    public TrainingProgram getMajorTrainingProgram(String major) {
        return userDao.getTrainingProgramByMajor(major);
    }

    @Override
    public List<TrainingProgram> getAllTrainingProgram() {
        return userDao.getAllTrainingProgram();
    }

    @Override
    public int deleteMajorTrainingProgram(String major) {
        return userDao.deleteTrainingProgramByMajor(major);
    }
}
