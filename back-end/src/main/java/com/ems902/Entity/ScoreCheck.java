package com.ems902.Entity;

public class ScoreCheck {
    private int checkId;
    private int sId;
    private int tId;
    private int cId;
    private int old_score;
    private int old_usual_score;
    private int old_final_score;
    private int new_usual_score;
    private int new_final_score;
    private int new_score;
    private String changeReason;
    private int adminCheck;


    public ScoreCheck() {
    }

    public ScoreCheck(int checkId, int sId, int tId, int cId, int old_score, int old_usual_score, int old_final_score, int new_usual_score, int new_final_score, int new_score, String changeReason, int adminCheck) {
        this.checkId = checkId;
        this.sId = sId;
        this.tId = tId;
        this.cId = cId;
        this.old_score = old_score;
        this.old_usual_score = old_usual_score;
        this.old_final_score = old_final_score;
        this.new_usual_score = new_usual_score;
        this.new_final_score = new_final_score;
        this.new_score = new_score;
        this.changeReason = changeReason;
        this.adminCheck = adminCheck;
    }

    public ScoreCheck(int sId, int tId, int cId, int old_score, int new_score, int old_usual_score, int old_final_score, int new_usual_score, int new_final_score, String changeReason, int adminCheck) {
        this.sId = sId;
        this.tId = tId;
        this.cId = cId;
        this.old_score = old_score;
        this.old_usual_score = old_usual_score;
        this.old_final_score = old_final_score;
        this.new_usual_score = new_usual_score;
        this.new_final_score = new_final_score;
        this.new_score = new_score;
        this.changeReason = changeReason;
        this.adminCheck = adminCheck;
    }

    public int getOld_usual_score() {
        return old_usual_score;
    }

    public void setOld_usual_score(int old_usual_score) {
        this.old_usual_score = old_usual_score;
    }

    public int getOld_final_score() {
        return old_final_score;
    }

    public void setOld_final_score(int old_final_score) {
        this.old_final_score = old_final_score;
    }

    public int getNew_usual_score() {
        return new_usual_score;
    }

    public void setNew_usual_score(int new_usual_score) {
        this.new_usual_score = new_usual_score;
    }

    public int getNew_final_score() {
        return new_final_score;
    }

    public void setNew_final_score(int new_final_score) {
        this.new_final_score = new_final_score;
    }

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public int getOld_score() {
        return old_score;
    }

    public void setOld_score(int old_score) {
        this.old_score = old_score;
    }

    public int getNew_score() {
        return new_score;
    }

    public void setNew_score(int new_score) {
        this.new_score = new_score;
    }

    public String getChangeReason() {
        return changeReason;
    }

    public void setChangeReason(String changeReason) {
        this.changeReason = changeReason;
    }

    public int getAdminCheck() {
        return adminCheck;
    }

    public void setAdminCheck(int adminCheck) {
        this.adminCheck = adminCheck;
    }
}
