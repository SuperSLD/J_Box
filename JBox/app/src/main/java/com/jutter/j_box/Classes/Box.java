package com.jutter.j_box.Classes;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Box {
    private int id;
    private int pointId;
    private String startDate;
    private String endDate;
    private int userId;
    private String type;

    public Box (int id, int pointId, String endDate, int userId, String type) {
        this.id = id;
        this.pointId = pointId;
        this.startDate = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
        this.endDate = endDate;
        this.userId = userId;
        this.type = type;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPointId() {
        return pointId;
    }

    public void setPointId(int pointId) {
        this.pointId = pointId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
