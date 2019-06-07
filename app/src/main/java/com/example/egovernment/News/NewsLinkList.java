package com.example.egovernment.News;

import java.util.LinkedList;


public class NewsLinkList {
    public static final double p = 10;

    public static LinkedList<News> news;
    public static LinkedList<News> myNews;

    public static LinkedList<NewsHistory> newsHistories;
    public static LinkedList<NewsHistory> myNewsHistories;

    public static String phoneNumber;
    public static LinkedList<String> friends;
    public static LinkedList<String> people;

    public static void mkNews(){
        news = new LinkedList<>();
        newsHistories = new LinkedList<>();
    }

    public static void mkHistory(){
        myNewsHistories = new LinkedList<>();
        myNews = new LinkedList<>();
        for (NewsHistory n:newsHistories) {
            if (n.getPhoneNumber().equals(phoneNumber)){
                myNewsHistories.addLast(n);
                for (News s:news) {
                    if (s.getId() == n.getNewsId()){
                        myNews.addLast(s);
                        break;
                    }
                }
            }
        }
        for (NewsHistory n:newsHistories) {
            if (!people.equals(n.getPhoneNumber().intern())){
                people.addLast(n.getPhoneNumber());
            }
        }
    }

    public static void findFriend(){
        friends = new LinkedList<>();
        for (String s:people){
            for (NewsHistory h:myNewsHistories){

            }
        }
    }
}
