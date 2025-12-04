package com.edu.transaction.impl;

import com.edu.service.CashDispenserChain;
import com.edu.model.Account;
import com.edu.model.BankDatabase;
import com.edu.service.dispenser.Bill100Dispenser;
import com.edu.service.dispenser.Bill500Dispenser;
import com.edu.view.UserInterface;

public class Withdrawal extends BaseTransaction {
    private final CashDispenserChain dispenserChain;

    public Withdrawal(UserInterface ui, BankDatabase database, int accountNumber) {
        super(ui, database, accountNumber);
        
        // Chain of Responsibility
        CashDispenserChain c1 = new Bill500Dispenser();
        CashDispenserChain c2 = new Bill100Dispenser();
        c1.setNextChain(c2);
        this.dispenserChain = c1;
    }

    @Override
    protected void performTransaction() {
        ui.showMessage("Введіть суму для зняття (кратну 100):");
        double amount = ui.readDouble();
        

        if (amount % 100 != 0) {
            ui.showMessage("Сума має бути кратна 100.");
            return;
        }

        Account account = database.getAccount(accountNumber);
        if (account.getBalance() >= amount) {
            account.debit(amount);
            ui.showMessage("Готівку видано.");
            
            // Запускаємо ланцюжок видачі купюр
            dispenserChain.dispense((int) amount);
        } else {
            ui.showMessage("Недостатньо коштів.");
        }
    }
}