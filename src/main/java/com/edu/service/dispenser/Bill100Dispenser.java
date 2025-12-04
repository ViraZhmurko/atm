package com.edu.service.dispenser;

import com.edu.service.CashDispenserChain;

public class Bill100Dispenser extends CashDispenserChain {
    public Bill100Dispenser() { this.billSize = 100; }
}