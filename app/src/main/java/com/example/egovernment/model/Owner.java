package com.example.egovernment.model;

public class Owner {
    private String card_number;
    private String date;
    private String type;
    private int amount;
    private int bank_balance;

    public Owner(String card_number, String date, String type, int amount, int bank_balance) {
        this.card_number = card_number;
        this.date = date;
        this.type = type;
        this.amount = amount;
        this.bank_balance = bank_balance;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getBank_balance() {
        return bank_balance;
    }

    public void setBank_balance(int bank_balance) {
        this.bank_balance = bank_balance;
    }
}
