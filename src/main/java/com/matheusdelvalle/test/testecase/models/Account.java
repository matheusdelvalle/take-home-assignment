package com.matheusdelvalle.test.testecase.models;

public class Account {

    private String id;
    private int balance;

    public Account() {}

    public Account(String id, int balance) {
        this.id = id;
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}

