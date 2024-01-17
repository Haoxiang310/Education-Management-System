package com.ems902.Entity;

public class AdminCourse {
    private int acId;       //admin-course主键
    private int adminId;    //教务id
    private int cId;        //课程id
    private String cName;   //课程名

    public AdminCourse() {
    }

    public AdminCourse(int acId, int adminId, int cId, String cName) {
        this.acId = acId;
        this.adminId = adminId;
        this.cId = cId;
        this.cName = cName;
    }

    public int getAcId() {
        return acId;
    }

    public void setAcId(int acId) {
        this.acId = acId;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
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

    @Override
    public String toString() {
        return "AdminCourse{" +
                "acId=" + acId +
                ", aId=" + adminId +
                ", cId=" + cId +
                ", cName='" + cName + '\'' +
                '}';
    }
}
