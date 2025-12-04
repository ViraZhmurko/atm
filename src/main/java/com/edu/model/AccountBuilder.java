package com.edu.model;

//builder
public class AccountBuilder {
    private int accountNumber;
    private int pin;
    private double balance;

    public AccountBuilder setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public AccountBuilder setPin(int pin) {
        this.pin = pin;
        return this;
    }

    public AccountBuilder setBalance(double balance) {
        this.balance = balance;
        return this;
    }

    public Account build() {
        return new Account(accountNumber, pin, balance);
    }
}