package com.edu.model;

public class Account {
    private final int accountNumber;
    private final int pin;
    private double totalBalance;


    Account(int accountNumber, int pin, double totalBalance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.totalBalance = totalBalance;
    }

    public boolean validatePin(int userPin) {
        return userPin == pin;
    }

    public double getBalance() {
        return totalBalance;
    }

    public void credit(double amount) {
        this.totalBalance += amount;
    }

    public void debit(double amount) {
        this.totalBalance -= amount;
    }

    public int getAccountNumber() {
        return accountNumber;
    }
}