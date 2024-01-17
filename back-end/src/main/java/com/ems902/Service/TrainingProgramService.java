package com.ems902.Service;

import com.alibaba.fastjson.JSON;
import com.ems902.Entity.Lesson;
import com.ems902.Entity.TrainingProgram;

import java.util.List;
import java.util.Map;

public interface TrainingProgramService {
    //设置培养方案
    public int setTrainingProgram(TrainingProgram trainingProgram);
    //更新指定专业的培养方案的课程
    public int updateTrainingProgramCourse(String lessonString,String major);
    //查找选定专业的培养方案
    public TrainingProgram getMajorTrainingProgram(String major);
    //获取所有的专业培养方案
    public List<TrainingProgram> getAllTrainingProgram();
    //删除某专业的培养计划
    public int deleteMajorTrainingProgram(String major);

}