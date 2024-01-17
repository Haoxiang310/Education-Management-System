package com.ems902.Entity;

public class StuCourse {
    private int sId;
    private int cId;
    private int chooseId;
    private int grade;
    private int usual_grade;
    private int final_grade;

    public StuCourse(int sId, int cId, int grade) {
        this.sId = sId;
        this.cId = cId;
        this.grade = grade;
    }

    public StuCourse(int sId, int cId, int chooseId, int grade) {
        this.sId = sId;
        this.cId = cId;
        this.chooseId = chooseId;
        this.grade = grade;
    }

    public int getUsual_grade() {
        return usual_grade;
    }

    public void setUsual_grade(int usual_grade) {
        this.usual_grade = usual_grade;
    }

    public int getFinal_grade() {
        return final_grade;
    }

    public void setFinal_grade(int final_grade) {
        this.final_grade = final_grade;
    }

    public StuCourse(int sId, int cId, int grade, int usual_grade, int final_grade) {
        this.sId = sId;
        this.cId = cId;
        this.grade = grade;
        this.usual_grade = usual_grade;
        this.final_grade = final_grade;
    }

    public StuCourse() {
    }

    public StuCourse(int sId, int cId) {
        this.sId = sId;
        this.cId = cId;
    }

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public int getChooseId() {
        return chooseId;
    }

    public void setChooseId(int chooseId) {
        this.chooseId = chooseId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
