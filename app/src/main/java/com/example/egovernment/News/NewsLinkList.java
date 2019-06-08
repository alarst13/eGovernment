package com.example.egovernment.News;

import android.content.Context;

import java.util.LinkedList;


public class NewsLinkList {
    public static final double p = 50;

    public static LinkedList<News> news;
    public static LinkedList<News> myNews;
    public static LinkedList<News> newsForShow;

    public static LinkedList<NewsHistory> newsHistories;
    public static LinkedList<NewsHistory> myNewsHistories;

    public static String phoneNumber;
    public static LinkedList<String> friends;
    public static LinkedList<String> people;

    public static void mkNews(Context context){
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
            boolean b = true;
            for (NewsHistory h:myNewsHistories){
                if (searchHistory(s,h.getNewsId()) != null){
                    b = false;
                    break;
                }
            }
            if (b){
                friends.addLast(s);
            }
        }

        for (String s : friends){
            if (!isFriend(s)){
                friends.remove(s);
            }
        }
    }

    public static NewsHistory searchHistory(String phone , int id){
        for (NewsHistory h : newsHistories) {
            if (h.getNewsId() == id && h.getPhoneNumber().equals(phone)){
                return  h;
            }
        }
        return null;
    }

    public static boolean isFriend(String s){
        double sum = 0;
        for (NewsHistory h : myNewsHistories){
            NewsHistory k =  searchHistory(s , h.getNewsId());
            try {
                sum += (h.getNewsPoint() - k.getNewsPoint()) * (h.getNewsPoint() - k.getNewsPoint());
            }catch (Exception e){
                return false;
            }
        }
        if (sum <= p){
            return true;
        }
        return false;
    }

    public static LinkedList<News> mkShowList(Context context , String phone){
        mkNews(context);
        NewsLinkList.phoneNumber = phone;
        mkHistory();
        findFriend();
        return null;
    }
}
