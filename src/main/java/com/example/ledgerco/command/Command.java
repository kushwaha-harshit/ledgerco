package com.example.ledgerco.command;

import java.util.Objects;

public abstract class Command {

    protected Command(String bankName, String borrowerName){
        this.bankName = bankName;
        this.borrowerName = borrowerName;
    }

    protected String bankName;
    protected String borrowerName;

    public String getBankName() {
        return bankName;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Command)) return false;
        Command command = (Command) o;
        return Objects.equals(bankName, command.bankName) && Objects.equals(borrowerName, command.borrowerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankName, borrowerName);
    }
}
