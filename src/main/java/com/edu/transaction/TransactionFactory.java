package com.edu.transaction;

import com.edu.model.BankDatabase;
import com.edu.transaction.impl.BalanceInquiry;
import com.edu.transaction.impl.Deposit;
import com.edu.transaction.impl.Withdrawal;
import com.edu.view.UserInterface;

//породжувальний паттерн (фабрика)
public class TransactionFactory {
    
    public static Transaction createTransaction(int type, UserInterface ui, BankDatabase db, int currentAccountNumber) {
        return switch (type) {
            case 1 -> new BalanceInquiry(ui, db, currentAccountNumber);
            case 2 -> new Withdrawal(ui, db, currentAccountNumber);
            case 3 -> new Deposit(ui, db, currentAccountNumber);
            default -> null;
        };
    }
}