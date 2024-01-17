package com.ems902.Entity;

public class Classroom {
    private int roomId;        //room id号
    private String place;      //room 地点
    private int week;          //有课的周数
    private int day;           //星期几有课
    private int time;          //当天的第几节有课
    private int capacity;      //教室容量

    public Classroom() {
    }

    public Classroom(String place, int week, int day, int time, int capacity) {
        this.place = place;
        this.week = week;
        this.day = day;
        this.time = time;
        this.capacity = capacity;
    }

    public Classroom(int roomId, String place, int week, int day, int time, int capacity) {
        this.roomId = roomId;
        this.place = place;
        this.week = week;
        this.day = day;
        this.time = time;
        this.capacity = capacity;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "roomId=" + roomId +
                ", place='" + place + '\'' +
                ", week=" + week +
                ", day=" + day +
                ", time=" + time +
                ", capacity=" + capacity +
                '}';
    }
}
