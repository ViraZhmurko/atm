package com.edu.transaction.impl;

import com.edu.model.Account;
import com.edu.model.BankDatabase;
import com.edu.view.UserInterface;

public class BalanceInquiry extends BaseTransaction {

    public BalanceInquiry(UserInterface ui, BankDatabase database, int accountNumber) {
        super(ui, database, accountNumber);
    }

    @Override
    protected void performTransaction() {
        Account account = database.getAccount(accountNumber);
        ui.showMessage("Поточний баланс: " + account.getBalance());
    }
}