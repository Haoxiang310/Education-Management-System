package com.ems902.Entity;

public class Admin {
    private int adminId;                //教务id号
    private String adminPwd;            //教务密码
    private String adminSchool;         //教务所属学院

    public Admin() {
    }

    public Admin(int adminId, String adminPwd, String adminSchool) {
        this.adminId = adminId;
        this.adminPwd = adminPwd;
        this.adminSchool = adminSchool;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getAdminSchool() {
        return adminSchool;
    }

    public void setAdminSchool(String adminSchool) {
        this.adminSchool = adminSchool;
    }

    public String getAdminPwd() {
        return adminPwd;
    }

    public void setAdminPwd(String adminPwd) {
        this.adminPwd = adminPwd;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", adminPwd='" + adminPwd + '\'' +
                ", adminSchool='" + adminSchool + '\'' +
                '}';
    }
}
