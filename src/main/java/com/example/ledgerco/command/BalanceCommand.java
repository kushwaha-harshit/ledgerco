package com.example.ledgerco.command;

import java.util.Objects;

public class BalanceCommand extends Command{
    private final Integer emiNumber;

    public BalanceCommand(String bankName, String borrowerName, Integer emiNumber){
        super(bankName, borrowerName);
        this.emiNumber = emiNumber;
    }

    public Integer getEmiNumber() {
        return emiNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BalanceCommand)) return false;
        if (!super.equals(o)) return false;
        BalanceCommand that = (BalanceCommand) o;
        return Objects.equals(emiNumber, that.emiNumber)
                && Objects.equals(bankName, that.bankName)
                && Objects.equals(borrowerName, that.borrowerName) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), emiNumber);
    }
}
