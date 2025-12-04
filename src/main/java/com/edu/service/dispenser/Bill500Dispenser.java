package com.edu.service.dispenser;

import com.edu.service.CashDispenserChain;

public class Bill500Dispenser extends CashDispenserChain {
    public Bill500Dispenser() { this.billSize = 500; }
}