package com.example.projet;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName ="transaction")
public class Transaction {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private double amount;
    private String type; // "income" or "expense"
    private String category;
    private String date;
    private String note;


    public Transaction(double amount, String type, String category, String date, String note) {

        this.amount = amount;
        this.type = type;
        this.category = category;
        this.date = date;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setId(int id) {
        this.id = id;
    }
}