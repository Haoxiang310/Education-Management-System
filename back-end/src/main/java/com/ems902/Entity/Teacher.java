package com.ems902.Entity;

public class Teacher {
    private int tId;
    private String tPwd;
    private String tName;
    private String Ins;

    public Teacher() {

    }
    public Teacher(int tId, String tPwd, String tName, String ins) {
        this.tId = tId;
        this.tPwd = tPwd;
        this.tName = tName;
        this.Ins = ins;
    }

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }

    public String gettPwd() {
        return tPwd;
    }

    public void settPwd(String tPwd) {
        this.tPwd = tPwd;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String getIns() {
        return Ins;
    }

    public void setIns(String ins) {
        Ins = ins;
    }
}
