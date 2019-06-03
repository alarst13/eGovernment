package com.example.egovernment.QuizOfKings;

import java.util.LinkedList;

public class QuizOfKings {
    LinkedList <String> questions;
    LinkedList <String> answer1;
    LinkedList <String> answer2;
    LinkedList <String> answer3;
    LinkedList <String> answer4;
    LinkedList <String> answer;

    public QuizOfKings() {
        //get from data base
        questions = new LinkedList<>();
        answer1 = new LinkedList<>();
        answer2 = new LinkedList<>();
        answer3 = new LinkedList<>();
        answer4 = new LinkedList<>();
        answer = new LinkedList<>();

        questions.addLast("1");
        answer1.addLast("1");
        answer2.addLast("2");
        answer3.addLast("3");
        answer4.addLast("4");
        answer.addLast("1");

        questions.addLast("2");
        answer1.addLast("1");
        answer2.addLast("2");
        answer3.addLast("3");
        answer4.addLast("4");
        answer.addLast("2");

        questions.addLast("3");
        answer1.addLast("1");
        answer2.addLast("2");
        answer3.addLast("3");
        answer4.addLast("4");
        answer.addLast("3");

        questions.addLast("4");
        answer1.addLast("1");
        answer2.addLast("2");
        answer3.addLast("3");
        answer4.addLast("4");
        answer.addLast("4");
    }
}
