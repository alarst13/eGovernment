package com.example.egovernment.News;

import android.os.Bundle;

import java.io.Serializable;
import java.util.LinkedList;

public class NewsLinkList implements Serializable {
    public LinkedList<News> news;

    public NewsLinkList(LinkedList<News> news) {
        this.news = news;
    }
}
