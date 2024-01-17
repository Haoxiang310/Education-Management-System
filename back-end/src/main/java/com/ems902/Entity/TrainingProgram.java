package com.ems902.Entity;

import com.alibaba.fastjson.JSON;

import java.util.List;

public class TrainingProgram {
    private int programId;                  //培养方案主键id
    private String major;                   //专业名称
    private String admission;               //专业入学要求
    private int lengthOfSchooling;          //修学年限
    //private List<Lesson> courseList;      //学习课程
    private String lessonList;              //课程json形式的string，长度大小512，能大概存3个lesson。。。

    public TrainingProgram() {
    }

    public TrainingProgram(int programId, String major, String admission, int lengthOfSchooling, String lessonList) {
        this.programId = programId;
        this.major = major;
        this.admission = admission;
        this.lengthOfSchooling = lengthOfSchooling;
        this.lessonList = lessonList;
    }

    public TrainingProgram(String major, String admission, int lengthOfSchooling, String lessonList) {
        this.major = major;
        this.admission = admission;
        this.lengthOfSchooling = lengthOfSchooling;
        this.lessonList = lessonList;
    }

    public TrainingProgram(String major, String admission, int lengthOfSchooling) {
        this.major = major;
        this.admission = admission;
        this.lengthOfSchooling = lengthOfSchooling;
    }

    public int getProgramId() {
        return programId;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getAdmission() {
        return admission;
    }

    public void setAdmission(String admission) {
        this.admission = admission;
    }

    public int getLengthOfSchooling() {
        return lengthOfSchooling;
    }

    public void setLengthOfSchooling(int lengthOfSchooling) {
        this.lengthOfSchooling = lengthOfSchooling;
    }

    public String getLessonList() {
        return lessonList;
    }

    public void setLessonList(String lessonList) {
        this.lessonList = lessonList;
    }

    @Override
    public String toString() {
        return "TrainingProgram{" +
                "programId=" + programId +
                ", major='" + major + '\'' +
                ", admission='" + admission + '\'' +
                ", lengthOfSchooling=" + lengthOfSchooling +
                ", lessonList='" + lessonList + '\'' +
                '}';
    }
}


