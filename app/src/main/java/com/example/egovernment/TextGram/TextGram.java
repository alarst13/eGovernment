package com.example.egovernment.TextGram;

import java.util.LinkedList;

public class TextGram {
    public static String thisPhone;
    public static String thatPhone;

    public static LinkedList<TextMessage> textMessages ;
    public static LinkedList<TextMessage> textGramActivityList;
    public static LinkedList<TextMessage> textGramMAList;


    private TextGram(){
    }

    public static void getFromDB(){
        textMessages = new LinkedList<>();
        textGramActivityList = new LinkedList<>();
        textGramMAList = new LinkedList<>();
        textMessages.addLast(new TextMessage("09129567534" , "09188876361" , "hello" , 1));
        textMessages.addLast(new TextMessage("09188876361" , "09129567534" , "yes" , 2));
        textMessages.addLast(new TextMessage("09129567534" , "0911" , "no" , 1));
        textMessages.addLast(new TextMessage("0" , "1" , "<>" , 1));
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
        textGramMAList.clear();

        for (TextMessage t:textMessages) {
            if ( (t.getSender().equals(thisPhone) && t.getReceiver().equals(thatPhone)) ||
                    (t.getSender().equals(thatPhone) && t.getReceiver().equals(thisPhone))){
                texts.addLast(t);
            }
        }

        for (int i = 1; i <= texts.size(); i++) {
            for (TextMessage t : texts) {
                if (t.getNumber() == i){
                    textGramMAList.addLast(t);
                }
            }
        }
    }

    public static void mkMessage(String receiver , String s){
        int x = 0;
        for (TextMessage t:textMessages) {
            if ( (t.getSender().equals(thisPhone) && t.getReceiver().equals(receiver)) ||
                    (t.getSender().equals(receiver) && t.getReceiver().equals(thisPhone)) ){
                if (x < t.getNumber()) {
                    x = t.getNumber();
                }
            }
        }
        TextMessage textMessage = new TextMessage(thisPhone , receiver , s , x + 1);
        textMessages.addLast(textMessage);
        textGramMAList.addLast(textMessage);
    }
}
