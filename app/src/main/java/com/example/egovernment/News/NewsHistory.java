package com.example.egovernment.News;

public class NewsHistory {
    private int newsId;
    private int newsPoint;
    private String phoneNumber;

    public NewsHistory(int newsId, int newsPoint, String phoneNumber) {
        this.newsId = newsId;
        this.newsPoint = newsPoint;
        this.phoneNumber = phoneNumber;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public int getNewsPoint() {
        return newsPoint;
    }

    public void setNewsPoint(int newsPoint) {
        this.newsPoint = newsPoint;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
