package com.edu.service;

public abstract class CashDispenserChain {
    protected CashDispenserChain nextChain;
    protected int billSize;

    public void setNextChain(CashDispenserChain nextChain) {
        this.nextChain = nextChain;
    }

    public void dispense(int amount) {
        if (amount >= billSize) {
            int num = amount / billSize;
            int remainder = amount % billSize;
            System.out.println("Видано " + num + " купюр по " + billSize);
            if (remainder != 0 && nextChain != null) {
                nextChain.dispense(remainder);
            }
        } else if (nextChain != null) {
            nextChain.dispense(amount);
        }
    }
}


