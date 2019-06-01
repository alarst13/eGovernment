package com.example.egovernment.Dictionary;

public class Word {
    private String persian;
    private String english;

    public Word(String persian, String english) {
        this.persian = persian;
        this.english = english;
    }

    public String getPersian() {
        return persian;
    }

    public void setPersian(String persian) {
        this.persian = persian;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }
}
