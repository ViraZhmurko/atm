package com.edu.transaction.impl;

import com.edu.model.Account;
import com.edu.model.BankDatabase;
import com.edu.view.UserInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class BalanceInquiryTest {

    private final int ACCOUNT_NUM = 12345;
    private final double CURRENT_BALANCE = 5750.50;

    @Mock
    private UserInterface ui;
    @Mock
    private BankDatabase database;
    @Mock
    private Account account;

    private BalanceInquiry balanceInquiry;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        when(database.getAccount(ACCOUNT_NUM)).thenReturn(account);

        balanceInquiry = new BalanceInquiry(ui, database, ACCOUNT_NUM);
    }

    @Test
    void performTransaction_DisplaysCorrectBalance() {
        when(account.getBalance()).thenReturn(CURRENT_BALANCE);

        balanceInquiry.performTransaction();

        verify(account, times(1)).getBalance();

        String expectedMessage = "Поточний баланс: " + CURRENT_BALANCE;
        verify(ui, times(1)).showMessage(expectedMessage);
    }

    @Test
    void execute_CallsStartAndEndMessages() {
        when(account.getBalance()).thenReturn(CURRENT_BALANCE);

        balanceInquiry.execute();

        verify(ui, times(1)).showMessage("--- Початок операції ---");
        verify(ui, times(1)).showMessage("Поточний баланс: " + CURRENT_BALANCE);
        verify(ui, times(1)).showMessage("--- Кінець операції ---");
    }
}