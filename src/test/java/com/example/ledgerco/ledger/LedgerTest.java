package com.example.ledgerco.ledger;

import com.example.ledgerco.command.BalanceCommand;
import com.example.ledgerco.command.LoanCommand;
import com.example.ledgerco.command.PaymentCommand;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LedgerTest {

    @Test
    void shouldCreateALedger(){
        assertDoesNotThrow(() -> {
            Ledger ledger = Ledger.create();
            assertNotNull(ledger);
        });
    }

    @Test
    void shouldExecuteCommands(){
        assertDoesNotThrow(() -> {
            Ledger ledger = Ledger.create();
            ledger.executeCommands(Arrays.asList(
                    new LoanCommand("UON", "Shelly", 15000, 2, 9.0),
                    new PaymentCommand("UON", "Shelly",  7000, 12),
                    new BalanceCommand("UON", "Shelly", 12)
            ));
        });
    }

}