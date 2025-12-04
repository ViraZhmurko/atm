package com.edu.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BankDatabaseTest {

    private BankDatabase database;
    private final int ACCOUNT_1_NUM = 12345;
    private final int ACCOUNT_1_PIN = 1111;

    @BeforeEach
    void setUp() {
        database = BankDatabase.getInstance();
    }

    @Test
    void getInstance_ReturnsSameInstance() {
        BankDatabase anotherInstance = BankDatabase.getInstance();
        assertSame(database, anotherInstance, " getInstance() має повертати той самий екземпляр (Singleton)");
    }

    @Test
    void getAccount_ExistingAccount_ReturnsAccount() {
        Account account = database.getAccount(ACCOUNT_1_NUM);
        assertNotNull(account, "Рахунок 12345 має існувати");
        assertEquals(ACCOUNT_1_NUM, account.getAccountNumber());
    }

    @Test
    void getAccount_NonExistingAccount_ReturnsNull() {
        Account account = database.getAccount(99999);
        assertNull(account, "Рахунок 99999 не має існувати");
    }

    @Test
    void authenticateUser_ValidCredentials_ReturnsTrue() {
        assertTrue(database.authenticateUser(ACCOUNT_1_NUM, ACCOUNT_1_PIN), "Автентифікація має бути успішною");
    }

    @Test
    void authenticateUser_InvalidPin_ReturnsFalse() {
        assertFalse(database.authenticateUser(ACCOUNT_1_NUM, 9999), "Автентифікація має бути неуспішною через невірний пін");
    }

    @Test
    void authenticateUser_InvalidAccount_ReturnsFalse() {
        assertFalse(database.authenticateUser(99999, ACCOUNT_1_PIN), "Автентифікація має бути неуспішною через неіснуючий рахунок");
    }
}