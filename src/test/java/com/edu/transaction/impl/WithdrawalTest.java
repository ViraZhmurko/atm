package com.edu.transaction.impl;

import com.edu.model.Account;
import com.edu.model.BankDatabase;
import com.edu.view.UserInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class WithdrawalTest {

    private final int ACCOUNT_NUM = 12345;
    private final double INITIAL_BALANCE = 1000.0;

    @Mock
    private UserInterface ui;
    @Mock
    private BankDatabase database;
    @Mock
    private Account account;
    private Withdrawal withdrawal;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        when(database.getAccount(ACCOUNT_NUM)).thenReturn(account);

        withdrawal = new Withdrawal(ui, database, ACCOUNT_NUM);

        when(account.getBalance()).thenReturn(INITIAL_BALANCE);
    }

    @Test
    void performTransaction_SuccessfulWithdrawal() {
        double amount = 500.0;

        when(ui.readDouble()).thenReturn(amount);

        withdrawal.performTransaction();

        verify(account, times(1)).debit(amount);
        verify(ui).showMessage("Готівку видано.");

    }

    @Test
    void performTransaction_InsufficientFunds() {
        double amount = 1500.0;

        when(ui.readDouble()).thenReturn(amount);
        when(account.getBalance()).thenReturn(INITIAL_BALANCE);

        withdrawal.performTransaction();

        verify(account, never()).debit(anyDouble());
        verify(ui).showMessage("Недостатньо коштів.");
    }

    @Test
    void performTransaction_AmountNotMultipleOf100() {
        double amount = 450.0;

        when(ui.readDouble()).thenReturn(amount);

        withdrawal.performTransaction();

        verify(account, never()).debit(anyDouble());

        verify(ui).showMessage("Сума має бути кратна 100.");
    }
}