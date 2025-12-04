package com.edu.service;

import com.edu.service.dispenser.Bill100Dispenser;
import com.edu.service.dispenser.Bill500Dispenser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CashDispenserChainTest {

    private CashDispenserChain dispenserChain;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        CashDispenserChain c500 = new Bill500Dispenser();
        CashDispenserChain c100 = new Bill100Dispenser();
        c500.setNextChain(c100);
        this.dispenserChain = c500;

        System.setOut(new PrintStream(outContent));
    }

    @org.junit.jupiter.api.AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void dispense_AmountIsExact500() {
        dispenserChain.dispense(500);
        String output = outContent.toString();
        assertTrue(output.contains("Видано 1 купюр по 500"), "Має видати 1x500");
        assertTrue(output.lines().count() == 1, "Має бути лише одне повідомлення");
    }

    @Test
    void dispense_AmountIs700_UsesBothDispensers() {
        dispenserChain.dispense(700);
        String output = outContent.toString();
        assertTrue(output.contains("Видано 1 купюр по 500"), "Має видати 1x500");
        assertTrue(output.contains("Видано 2 купюр по 100"), "Має видати 2x100");
    }

    @Test
    void dispense_AmountIs900_UsesBothDispensers() {
        dispenserChain.dispense(900);
        String output = outContent.toString();
        assertTrue(output.contains("Видано 1 купюр по 500"), "Має видати 1x500");
        assertTrue(output.contains("Видано 4 купюр по 100"), "Має видати 4x100");
    }

    @Test
    void dispense_AmountIsLessThanSmallestBill_DoesNothing() {
        dispenserChain.dispense(50);
        String output = outContent.toString();
        assertTrue(output.isEmpty(), "Не має видавати готівку, якщо сума менша за 100");
    }

    @Test
    void dispense_AmountIsMultipleOf100ButLessThan500() {
        dispenserChain.dispense(300);
        String output = outContent.toString();
        assertTrue(output.contains("Видано 3 купюр по 100"), "Має видати 3x100");
        assertTrue(output.lines().count() == 1, "Має бути лише одне повідомлення");
    }
}