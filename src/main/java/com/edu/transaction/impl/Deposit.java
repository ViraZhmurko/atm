package com.edu.transaction.impl;

import com.edu.model.BankDatabase;
import com.edu.view.UserInterface;

public class Deposit extends BaseTransaction {

    public Deposit(UserInterface ui, BankDatabase database, int accountNumber) {
        super(ui, database, accountNumber);
    }

    @Override
    protected void performTransaction() {
        ui.showMessage("Введіть суму депозиту:");
        double amount = ui.readDouble();
        
        if (amount > 0) {
            database.getAccount(accountNumber).credit(amount);
            ui.showMessage("Кошти успішно зараховано.");
        } else {
            ui.showMessage("Некоректна сума.");
        }
    }
}