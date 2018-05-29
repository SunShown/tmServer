package com.lilei.entity;

public class Question {
    public int questionId;
    public String title;
    public String qOne;
    public String qTwo;
    public String qThree;
    public String qFour;
    public int answer;

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int workId;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getqOne() {
        return qOne;
    }

    public void setqOne(String qOne) {
        this.qOne = qOne;
    }

    public String getqTwo() {
        return qTwo;
    }

    public void setqTwo(String qTwo) {
        this.qTwo = qTwo;
    }

    public String getqThree() {
        return qThree;
    }

    public void setqThree(String qThree) {
        this.qThree = qThree;
    }

    public String getqFour() {
        return qFour;
    }

    public void setqFour(String qFour) {
        this.qFour = qFour;
    }


    public int getWorkId() {
        return workId;
    }

    public void setWorkId(int workId) {
        this.workId = workId;
    }
}
