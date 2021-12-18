package com.example.ledgerco.ledger;

import com.example.ledgerco.command.Command;
import com.example.ledgerco.loan.LoanRepository;
import com.example.ledgerco.loan.LoanService;

import java.util.List;

public class Ledger {
    private final LoanService loanService;

    private Ledger() {
        LoanRepository loanRepository = new LoanRepository();
        this.loanService = new LoanService(loanRepository);
    }

    public static Ledger create() {
        return new Ledger();
    }

    public void executeCommands(List<Command> commands) {
        commands.forEach(loanService::handleCommand);
    }
}
