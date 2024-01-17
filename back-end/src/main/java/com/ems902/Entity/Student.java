package com.ems902.Entity;

public class Student {
    private String name;
    private int sId;
    private String sPwd;
    private int grade;
    private String class_;
    private String sSchool;
    private String major;
    private int reCredit;
    private int gainCredit;

    public Student() {

    }

    public Student(String name, int sId, String sPwd, int grade, String class_, String sSchool, String major, int reCredit, int gainCredit) {
        this.name = name;
        this.sId = sId;
        this.sPwd = sPwd;
        this.grade = grade;
        this.class_ = class_;
        this.sSchool = sSchool;
        this.major = major;
        this.reCredit = reCredit;
        this.gainCredit = gainCredit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public String getsPwd() {
        return sPwd;
    }

    public void setsPwd(String sPwd) {
        this.sPwd = sPwd;
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

    public String getsSchool() {
        return sSchool;
    }

    public void setsSchool(String sSchool) {
        this.sSchool = sSchool;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getReCredit() {
        return reCredit;
    }

    public void setReCredit(int reCredit) {
        this.reCredit = reCredit;
    }

    public int getGainCredit() {
        return gainCredit;
    }

    public void setGainCredit(int gainCredit) {
        this.gainCredit = gainCredit;
    }

}
