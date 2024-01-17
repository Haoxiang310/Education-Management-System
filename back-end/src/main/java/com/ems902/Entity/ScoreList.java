package com.ems902.Entity;

public class ScoreList {
    //成绩单
    private String stuName; // 学生姓名
    private int sId;        //学生id
    private String cName;   //课程名
<<<<<<< HEAD
    private int cId;          //课程id
=======
    private int cId;     //课程id
>>>>>>> origin/master
    private int score;      //课程分数

    public ScoreList() {
    }

    public ScoreList(String stuName, int sId, String cName, int cId, int score) {
        this.stuName = stuName;
        this.sId = sId;
        this.cName = cName;
        this.cId = cId;
        this.score = score;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "ScoreList{" +
                "stuName='" + stuName + '\'' +
                ", sId=" + sId +
                ", cName='" + cName + '\'' +
                ", cId='" + cId + '\'' +
                ", score=" + score +
                '}';
    }
}
