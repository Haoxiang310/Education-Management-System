package com.ems902.Entity;

public class CourseClassroom {
    static private int primaryKey = 0;

    private int ccId;       // course-classroom id
    private int roomId;     // room id
    private int cId;   // course id

    public CourseClassroom() {

    }

    public CourseClassroom(int ccId, int roomId, int courseId) {
        this.ccId = ccId;
        this.roomId = roomId;
        this.cId = courseId;
    }

    public CourseClassroom(int roomId, int cId) {
        this.roomId = roomId;
        this.cId = cId;
    }

    public int getCcId() {
        return ccId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getCourseId() {
        return cId;
    }

    public void setCourseId(int cId) {
        this.cId = cId;
    }

    @Override
    public String toString() {
        return "CourseClassroom{" +
                "ccId=" + ccId +
                ", roomId=" + roomId +
                ", courseId=" + cId +
                '}';
    }
}
