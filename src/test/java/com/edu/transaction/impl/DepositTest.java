package com.edu.transaction.impl;

import com.edu.model.Account;
import com.edu.model.BankDatabase;
import com.edu.view.UserInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class DepositTest {

    private final int ACCOUNT_NUM = 12345;

    @Mock
    private UserInterface ui;
    @Mock
    private BankDatabase database;
    @Mock
    private Account account;

    private Deposit deposit;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        when(database.getAccount(ACCOUNT_NUM)).thenReturn(account);

        deposit = new Deposit(ui, database, ACCOUNT_NUM);
    }

    @Test
    void performTransaction_SuccessfulDeposit() {
        double amount = 350.0;

        when(ui.readDouble()).thenReturn(amount);

        deposit.performTransaction();

        verify(account, times(1)).credit(amount);

        verify(ui, times(1)).showMessage("Кошти успішно зараховано.");
    }

    @Test
    void performTransaction_ZeroAmount_ShowsError() {
        double amount = 0.0;

        when(ui.readDouble()).thenReturn(amount);

        deposit.performTransaction();

        verify(account, never()).credit(anyDouble());

        verify(ui, times(1)).showMessage("Некоректна сума.");
    }

    @Test
    void performTransaction_NegativeAmount_ShowsError() {
        double amount = -100.0;

        when(ui.readDouble()).thenReturn(amount);

        deposit.performTransaction();

        verify(account, never()).credit(anyDouble());

        verify(ui, times(1)).showMessage("Некоректна сума.");
    }
}