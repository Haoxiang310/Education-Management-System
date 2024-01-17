package com.ems902.Entity;

public class Course {
    private int cId;
    private String cName;
    private String cType;
    private int grade;
    private String class_;
    private String major;
    private int semester;
    private int credit;
    private String place;
    private int day; //星期几
    private int time;//第几节
    private int startweek;
    private int endweek;

    public Course() {
    }

    public Course(int cId, String cName, String cType, int grade, String class_,
                  String major, int semester, int credit, String place, int day,
                  int time, int startweek, int endweek) {
        this.cId = cId;
        this.cName = cName;
        this.cType = cType;
        this.grade = grade;
        this.class_ = class_;
        this.major = major;
        this.semester = semester;
        this.credit = credit;
        this.place = place;
        this.day = day;
        this.time = time;
        this.startweek = startweek;
        this.endweek = endweek;
    }

    public Course(String cName, String cType, int grade, String class_,
                  String major, int semester, int credit, String place,
                  int day, int time, int startweek, int endweek) {
        this.cName = cName;
        this.cType = cType;
        this.grade = grade;
        this.class_ = class_;
        this.major = major;
        this.semester = semester;
        this.credit = credit;
        this.place = place;
        this.day = day;
        this.time = time;
        this.startweek = startweek;
        this.endweek = endweek;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
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

    public String getClass_() {
        return class_;
    }

    public void setClass_(String class_) {
        this.class_ = class_;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
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
}

