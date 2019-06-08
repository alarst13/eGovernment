package com.example.egovernment.TextGram;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.TextView;

import com.example.egovernment.DatabaseAccess;
import com.example.egovernment.PreferenceData;
import com.example.egovernment.model.User;

import java.util.LinkedList;

public class TextGram {
    public static String thisPhone;
    public static String thatPhone;

    public static LinkedList<TextMessage> textMessages ;
    public static LinkedList<TextMessage> textGramActivityList;
    public static LinkedList<TextMessage> textGramList;


    private TextGram(){
    }

    public static void getFromDB(Context context){
        textMessages = new LinkedList<>();
        textGramActivityList = new LinkedList<>();
        textGramList = new LinkedList<>();

        String contact = PreferenceData.getLoggedInPhoneNumberUser(context);

        DatabaseAccess databaseAccess = new DatabaseAccess(context);
        Cursor c = databaseAccess.getDb().rawQuery("SELECT * FROM TextMessage WHERE sender = ? OR receiver = ?", new String[]{contact, contact});
        c.moveToFirst();
        while(!c.isAfterLast()){
            String sender = c.getString(0);
            String receiver = c.getString(1);
            String message = c.getString(2);
            int number = c.getInt(3);
            TextMessage textMessage = new TextMessage(sender, receiver, message, number);
            textMessages.addLast(textMessage);
            c.moveToNext();
        }
    }

    public static void mkTextGramActivityList(){
        textGramActivityList.clear();
        for (TextMessage t: textMessages) {
            if (t.getSender().equals(thisPhone) || t.getReceiver().equals(thisPhone)){
                boolean b = true;
                for (TextMessage m:textGramActivityList) {
                    if ( (t.getSender().equals(m.getSender()) && t.getReceiver().equals(m.getReceiver())) ||
                            (t.getSender().equals(m.getReceiver()) && t.getReceiver().equals(m.getSender())) ){
                        if (m.getNumber() > t.getNumber()){
                            b = false;
                        }else if (m.getNumber() < t.getNumber()){
                            textGramActivityList.remove(m);
                        }
                    }
                }
                if (b){
                    textGramActivityList.addLast(t);
                }
            }
        }
    }

    public static void mktextGramMAList(){
        LinkedList<TextMessage> texts = new LinkedList<>();
        textGramList.clear();

        for (TextMessage t:textMessages) {
            if ( (t.getSender().equals(thisPhone) && t.getReceiver().equals(thatPhone)) ||
                    (t.getSender().equals(thatPhone) && t.getReceiver().equals(thisPhone))){
                texts.addLast(t);
            }
        }

        for (int i = 1; i <= texts.size(); i++) {
            for (TextMessage t : texts) {
                if (t.getNumber() == i){
                    textGramList.addLast(t);
                }
            }
        }
    }

    public static void mkMessage(Context context, String receiver , String s){
        int x = 0;
        for (TextMessage t:textMessages) {
            if ( (t.getSender().equals(thisPhone) && t.getReceiver().equals(receiver)) ||
                    (t.getSender().equals(receiver) && t.getReceiver().equals(thisPhone)) ){
                if (x < t.getNumber()) {
                    x = t.getNumber();
                }
            }
        }

        DatabaseAccess databaseAccess = new DatabaseAccess(context);

        TextMessage textMessage = new TextMessage(thisPhone , receiver , s , x + 1);
        ContentValues contentValues = new ContentValues();
        contentValues.put("sender", textMessage.getSender());
        contentValues.put("receiver", textMessage.getReceiver());
        contentValues.put("message", textMessage.getMessage());
        contentValues.put("number", textMessage.getNumber());
        databaseAccess.getDb().insert("TextMessage", null, contentValues);
        textMessages.addLast(textMessage);
        textGramList.addLast(textMessage);
    }
}
