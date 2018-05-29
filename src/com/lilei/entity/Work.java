package com.lilei.entity;

import java.util.List;

public class Work {
    private int workId;
    private String workTitle;
    private String time;
    private int userId;
    private User user;

    public String getWorkTitle() {
        return workTitle;
    }

    public void setWorkTitle(String workTitle) {
        this.workTitle = workTitle;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private List<Question> workList;

    public List<Question> getWorkList() {
        return workList;
    }

    public void setWorkList(List<Question> workList) {
        this.workList = workList;
    }

    public int getWorkId() {
        return workId;
    }

    public void setWorkId(int workId) {
        this.workId = workId;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int user) {
        this.userId = user;
    }
}
