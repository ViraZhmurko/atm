package com.edu.model;

import java.util.HashMap;
import java.util.Map;

public class BankDatabase {
    private static BankDatabase instance;
    private Map<Integer, Account> accounts = new HashMap<>();

    private BankDatabase() {
        addAccount(new AccountBuilder().setAccountNumber(12345).setPin(1111).setBalance(1000.0).build());
        addAccount(new AccountBuilder().setAccountNumber(67890).setPin(2222).setBalance(500.0).build());
    }

    // Сінглтон
    public static BankDatabase getInstance() {
        if (instance == null) {
            instance = new BankDatabase();
        }
        return instance;
    }

    public Account getAccount(int accountNumber) {
        return accounts.get(accountNumber);
    }

    public boolean authenticateUser(int accountNumber, int userPin) {
        Account userAccount = getAccount(accountNumber);
        if (userAccount != null) {
            return userAccount.validatePin(userPin);
        }
        return false;
    }

    private void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }
}