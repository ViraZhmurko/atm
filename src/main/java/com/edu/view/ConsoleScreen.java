package com.edu.view;

import java.util.Scanner;

public class ConsoleScreen implements UserInterface {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.println("Будь ласка, введіть ціле число.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    @Override
    public double readDouble() {
        while (!scanner.hasNextDouble()) {
            System.out.println("Будь ласка, введіть суму.");
            scanner.next();
        }
        return scanner.nextDouble();
    }
}