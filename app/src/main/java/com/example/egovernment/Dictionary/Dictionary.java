package com.example.egovernment.Dictionary;

import java.util.LinkedList;

public class Dictionary {
    LinkedList <Word> words = new LinkedList<>();
    LinkedList <Word> wordsForShow = new LinkedList<>();

    public Dictionary() {
        //get from data base
        words.addLast(new Word("سیب" , "apple"));
        words.addLast(new Word("ایران" , "iran"));
        words.addLast(new Word("کتاب" , "book"));
        words.addLast(new Word("فلسفه" , "philosophy"));
        words.addLast(new Word("منطق" ,"logic"));
        words.addLast(new Word("ریاضی" , "mathematical"));
    }

    public boolean search(String string){
        wordsForShow.clear();
        string = string.toLowerCase();
        for (Word w:words) {
            if(w.getEnglish().contains(string) || w.getPersian().contains(string)){
                wordsForShow.addLast(w);
            }
        }
        if (wordsForShow.size() != 0){
            return true;
        }
        for(Word w : words){
            if(string.contains(w.getEnglish()) || string.contains(w.getPersian()) || checkString(w.getEnglish() , string) || checkString(w.getPersian() , string) ){
                wordsForShow.addLast(w);
            }
        }
        return false;
    }

    public static boolean checkString(String s1 , String s2){
        if (s1.length() != s2.length()){
            return false;
        }
        for (int i = 0; i < s1.length(); i++) {
            if (s2.substring(0 , i).equals(s1.substring(0 , i)) && s2.substring(i + 1).equals(s1.substring(i + 1))){
                return true;
            }
        }
        return false;
    }
}
