package com.edu.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    private final int ACCOUNT_NUMBER = 12345;
    private final int PIN = 1111;
    private final double INITIAL_BALANCE = 1000.0;
    private final Account account = new Account(ACCOUNT_NUMBER, PIN, INITIAL_BALANCE);

    @Test
    void validatePin_CorrectPin_ReturnsTrue() {
        assertTrue(account.validatePin(PIN), "Пін має бути валідним");
    }

    @Test
    void validatePin_IncorrectPin_ReturnsFalse() {
        assertFalse(account.validatePin(9999), "Пін має бути невалідним");
    }

    @Test
    void getBalance_ReturnsInitialBalance() {
        assertEquals(INITIAL_BALANCE, account.getBalance(), 0.001, "Початковий баланс має збігатися");
    }

    @Test
    void credit_IncreasesBalance() {
        double amount = 500.0;
        double expectedBalance = INITIAL_BALANCE + amount;

        account.credit(amount);

        assertEquals(expectedBalance, account.getBalance(), 0.001, "Баланс має збільшитися після зарахування");
    }

    @Test
    void debit_DecreasesBalance() {
        double amount = 200.0;
        double expectedBalance = INITIAL_BALANCE - amount;

        account.debit(amount);

        assertEquals(expectedBalance, account.getBalance(), 0.001, "Баланс має зменшитися після списання");
    }

    @Test
    void getAccountNumber_ReturnsCorrectNumber() {
        assertEquals(ACCOUNT_NUMBER, account.getAccountNumber(), "Номер рахунку має збігатися");
    }
}