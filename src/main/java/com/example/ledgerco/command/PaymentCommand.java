package com.example.ledgerco.command;

import java.util.Objects;

public class PaymentCommand extends Command{
    private final Integer lumpSumAmount;
    private final Integer emiNumber;

    public PaymentCommand(String bankName, String borrowerName, Integer lumpSumAmount, Integer emiNumber){
        super(bankName, borrowerName);
        this.emiNumber = emiNumber;
        this.lumpSumAmount = lumpSumAmount;
    }

    public Integer getLumpSumAmount() {
        return lumpSumAmount;
    }

    public Integer getEmiNumber() {
        return emiNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaymentCommand)) return false;
        if (!super.equals(o)) return false;
        PaymentCommand that = (PaymentCommand) o;
        return Objects.equals(lumpSumAmount, that.lumpSumAmount)
                && Objects.equals(bankName, that.bankName)
                && Objects.equals(borrowerName, that.borrowerName)
                && Objects.equals(emiNumber, that.emiNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), lumpSumAmount, emiNumber);
    }
}
