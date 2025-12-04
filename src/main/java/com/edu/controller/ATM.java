package com.edu.controller;

import com.edu.model.BankDatabase;
import com.edu.transaction.impl.BalanceInquiry;
import com.edu.view.ConsoleScreen;
import com.edu.transaction.Transaction;
import com.edu.transaction.TransactionFactory;
import com.edu.view.UserInterface;

// controller
public class ATM {
    private boolean userAuthenticated;
    private int currentAccountNumber;
    private UserInterface ui;
    private BankDatabase bankDatabase;

    public ATM() {
        this.userAuthenticated = false;
        this.currentAccountNumber = 0;
        this.ui = new ConsoleScreen();
        this.bankDatabase = BankDatabase.getInstance();
    }

    public void run() {
        while (true) {
            while (!userAuthenticated) {
                ui.showMessage("\n--- Вітаємо в Банкоматі! ---");
                authenticateUser();
            }
            performTransactions();
            userAuthenticated = false;
            currentAccountNumber = 0;
            ui.showMessage("\nДякуємо за використання!");
        }
    }

    private void authenticateUser() {
        ui.showMessage("Введіть номер рахунку:");
        int accountNumber = ui.readInt();
        ui.showMessage("Введіть PIN:");
        int pin = ui.readInt();

        userAuthenticated = bankDatabase.authenticateUser(accountNumber, pin);
        if (userAuthenticated) {
            currentAccountNumber = accountNumber;
        } else {
            ui.showMessage("Невірний номер рахунку або PIN.");
        }
    }

    private void performTransactions() {
        boolean userExited = false;
        while (!userExited) {
            int mainMenuSelection = displayMainMenu();
            if (mainMenuSelection == 4) {
                userExited = true;
            } else {

                // Withdrawal
                Transaction transaction = TransactionFactory.createTransaction(
                        mainMenuSelection, ui, bankDatabase, currentAccountNumber);
                
                // Command
                if (transaction != null) {
                    transaction.execute();
                }
                else {
                    ui.showMessage("Невірний вибір.");
                }
            }
        }
    }

    private int displayMainMenu() {
        ui.showMessage("\nМеню:");
        ui.showMessage("1 - Перевірити баланс");
        ui.showMessage("2 - Зняти кошти");
        ui.showMessage("3 - Внести кошти");
        ui.showMessage("4 - Вийти");
        ui.showMessage("Оберіть дію:");
        return ui.readInt();
    }
}