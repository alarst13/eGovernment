package com.example.egovernment.Dictionary;

import java.util.LinkedList;

public class Dictionary {
    LinkedList <Word> words = new LinkedList<>();
    LinkedList <Word> wordsForShow = new LinkedList<>();

    public Dictionary() {
        //get from data base
    }

    public void search(String string){
        wordsForShow.clear();
        string = string.toLowerCase();
        for (Word w:words) {
            if(w.getEnglish().contains(string) || w.getPersian().contains(string)){
                wordsForShow.addLast(w);
            }
        }

    }

}
