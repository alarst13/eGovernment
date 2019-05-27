package com.example.egovernment.News;

public class News {
    private String title;
    private String text;
    private String picID;
    private String type;

    public News(String title, String text, String picID, String type) {
        this.title = title;
        this.text = text;
        this.picID = picID;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
