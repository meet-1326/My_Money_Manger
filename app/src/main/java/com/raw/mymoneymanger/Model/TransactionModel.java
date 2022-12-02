package com.raw.mymoneymanger.Model;

public class TransactionModel {

    public String dates,category,extranotes;
    public int amount,type;

    public TransactionModel(String dates, String category, int amount, String extranotes, int type) {
        this.dates = dates;
        this.category = category;
        this.amount = amount;
        this.extranotes = extranotes;
        this.type = type;
    }
}
