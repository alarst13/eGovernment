package com.example.egovernment.QuizOfKings;

import android.content.Context;
import android.database.Cursor;

import com.example.egovernment.DatabaseAccess;
import com.example.egovernment.model.User;

import java.util.LinkedList;

public class QuizOfKings {
    LinkedList <String> questions;
    LinkedList <String> option1;
    LinkedList <String> option2;
    LinkedList <String> option3;
    LinkedList <String> option4;
    LinkedList <String> answer;

    public QuizOfKings(Context context) {
        questions = new LinkedList<>();
        option1 = new LinkedList<>();
        option2 = new LinkedList<>();
        option3 = new LinkedList<>();
        option4 = new LinkedList<>();
        answer = new LinkedList<>();
        DatabaseAccess databaseAccess = new DatabaseAccess(context);
        Cursor c = databaseAccess.getDb().rawQuery("SELECT * FROM Queries",null);
        c.moveToFirst();
        while(!c.isAfterLast()){
            String question = c.getString(0);
            String option_one = c.getString(1);
            String option_two = c.getString(2);
            String option_three = c.getString(3);
            String option_four = c.getString(4);
            String answer_from_database = c.getString(5);

            questions.addLast(question);
            option1.addLast(option_one);
            option2.addLast(option_two);
            option3.addLast(option_three);
            option4.addLast(option_four);
            answer.addLast(answer_from_database);

            c.moveToNext();
        }
    }
}
