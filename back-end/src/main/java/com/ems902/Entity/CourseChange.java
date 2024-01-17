package com.ems902.Entity;

public class CourseChange {
    private int cId;
    private int tId;
    private String reason;
    private String old_place;
    private String new_place;
    private int old_week;
    private int old_day;
    private int old_time;
    private int new_week;
    private int new_day;
    private int new_time;
    private int changeId;
    private int status;   //0,未审批；1，审批未通过；2，审批通过

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getOld_week() {
        return old_week;
    }

    public void setOld_week(int old_week) {
        this.old_week = old_week;
    }

    public int getOld_day() {
        return old_day;
    }

    public void setOld_day(int old_day) {
        this.old_day = old_day;
    }

    public int getOld_time() {
        return old_time;
    }

    public void setOld_time(int old_time) {
        this.old_time = old_time;
    }

    public int getNew_week() {
        return new_week;
    }

    public void setNew_week(int new_week) {
        this.new_week = new_week;
    }

    public int getNew_day() {
        return new_day;
    }

    public void setNew_day(int new_day) {
        this.new_day = new_day;
    }

    public int getNew_time() {
        return new_time;
    }

    public void setNew_time(int new_time) {
        this.new_time = new_time;
    }

    public int getChangeId() {
        return changeId;
    }

    public void setChangeId(int changeId) {
        this.changeId = changeId;
    }

    public String getOld_place() {
        return old_place;
    }

    public void setOld_place(String old_place) {
        this.old_place = old_place;
    }

    public String getNew_place() {
        return new_place;
    }

    public void setNew_place(String new_place) {
        this.new_place = new_place;
    }

    public CourseChange() {
    }

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public CourseChange(int cId, int tId, String reason, String old_place, String new_place, int old_week, int old_day, int old_time, int new_week, int new_day, int new_time, int changeId, int status) {

        this.cId = cId;
        this.tId = tId;
        this.reason = reason;
        this.old_place = old_place;
        this.new_place = new_place;
        this.old_week = old_week;
        this.old_day = old_day;
        this.old_time = old_time;
        this.new_week = new_week;
        this.new_day = new_day;
        this.new_time = new_time;
        this.changeId = changeId;
        this.status = status;
    }

    public CourseChange(int cId, int tId, String reason, String old_place, String new_place, int old_week, int old_day, int old_time, int new_week, int new_day, int new_time, int status) {
        this.cId = cId;
        this.tId = tId;
        this.reason = reason;
        this.old_place = old_place;
        this.new_place = new_place;
        this.old_week = old_week;
        this.old_day = old_day;
        this.old_time = old_time;
        this.new_week = new_week;
        this.new_day = new_day;
        this.new_time = new_time;
        this.status = status;
    }
}
