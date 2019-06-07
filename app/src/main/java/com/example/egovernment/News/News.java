package com.example.egovernment.News;

public class News {
    private String title;
    private String text;
    private String picID;
    private int id;

    public News(String title, String text, String picID, int id) {
        this.title = title;
        this.text = text;
        this.picID = picID;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPicID() {
        return picID;
    }

    public void setPicID(String picID) {
        this.picID = picID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
