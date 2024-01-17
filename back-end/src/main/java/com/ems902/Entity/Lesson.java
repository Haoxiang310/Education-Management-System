package com.ems902.Entity;

public class Lesson {
    private int lessonId;        //课程id
    private String lessonName;   //课程名
    private String lessonSchool; //课程所属学院
    private String cType;        //课程类型
    private int grade;           //开设年级
    private int semester;        //开设学期
    private int credit;          //学分
    private int startweek;       //开始周数
    private int endweek;         //结束周数

    public Lesson() {
    }

    public Lesson(String lessonName, String lessonSchool, String cType, int grade, int semester, int credit, int startweek, int endweek) {
        this.lessonName = lessonName;
        this.lessonSchool = lessonSchool;
        this.cType = cType;
        this.grade = grade;
        this.semester = semester;
        this.credit = credit;
        this.startweek = startweek;
        this.endweek = endweek;
    }

    public Lesson(int lessonId, String lessonName, String lessonSchool, String cType, int grade, int semester, int credit, int startweek, int endweek) {
        this.lessonId = lessonId;
        this.lessonName = lessonName;
        this.lessonSchool = lessonSchool;
        this.cType = cType;
        this.grade = grade;
        this.semester = semester;
        this.credit = credit;
        this.startweek = startweek;
        this.endweek = endweek;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getLessonSchool() {
        return lessonSchool;
    }

    public void setLessonSchool(String lessonSchool) {
        this.lessonSchool = lessonSchool;
    }

    public String getcType() {
        return cType;
    }

    public void setcType(String cType) {
        this.cType = cType;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getStartweek() {
        return startweek;
    }

    public void setStartweek(int startweek) {
        this.startweek = startweek;
    }

    public int getEndweek() {
        return endweek;
    }

    public void setEndweek(int endweek) {
        this.endweek = endweek;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "lessonId=" + lessonId +
                ", lessonName='" + lessonName + '\'' +
                ", lessonSchool='" + lessonSchool + '\'' +
                ", cType='" + cType + '\'' +
                ", grade=" + grade +
                ", semester=" + semester +
                ", credit=" + credit +
                ", startweek=" + startweek +
                ", endweek=" + endweek +
                '}';
    }
}
