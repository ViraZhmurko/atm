package com.edu.transaction.impl;

import com.edu.model.BankDatabase;
import com.edu.transaction.Transaction;
import com.edu.view.UserInterface;

public abstract class BaseTransaction implements Transaction {
    protected UserInterface ui;
    protected BankDatabase database;
    protected int accountNumber;

    public BaseTransaction(UserInterface ui, BankDatabase database, int accountNumber) {
        this.ui = ui;
        this.database = database;
        this.accountNumber = accountNumber;
    }

    @Override
    public final void execute() {
        ui.showMessage("--- Початок операції ---");
        performTransaction(); // abstract
        ui.showMessage("--- Кінець операції ---");
    }

    protected abstract void performTransaction();
}