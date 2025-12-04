package com.edu.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AccountBuilderTest {

    @Test
    void build_CreatesAccountWithCorrectParameters() {
        int accountNumber = 54321;
        int pin = 3333;
        double balance = 2500.50;

        Account account = new AccountBuilder()
                .setAccountNumber(accountNumber)
                .setPin(pin)
                .setBalance(balance)
                .build();

        assertNotNull(account, "Об'єкт Account не повинен бути null");
        assertEquals(accountNumber, account.getAccountNumber(), "Номер рахунку має бути коректним");
        assertTrue(account.validatePin(pin), "Пін має бути коректним");
        assertEquals(balance, account.getBalance(), 0.001, "Баланс має бути коректним");
    }
}