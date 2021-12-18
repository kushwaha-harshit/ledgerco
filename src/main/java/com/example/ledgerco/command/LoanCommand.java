package com.example.ledgerco.command;

import java.util.Objects;

public class LoanCommand extends Command{
    private final Integer principal;
    private final Integer tenureYears;
    private final Double interestRate;

    public LoanCommand(String bankName, String borrowerName, Integer principal, Integer tenureYears, Double interestRate){
        super(bankName, borrowerName);
        this.principal = principal;
        this.interestRate = interestRate;
        this.tenureYears = tenureYears;
    }

    public Integer getPrincipal() {
        return principal;
    }

    public Integer getTenureYears() {
        return tenureYears;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoanCommand)) return false;
        if (!super.equals(o)) return false;
        LoanCommand that = (LoanCommand) o;
        return Objects.equals(principal, that.principal)
                && Objects.equals(tenureYears, that.tenureYears)
                && Objects.equals(bankName, that.bankName)
                && Objects.equals(borrowerName, that.borrowerName)
                && Objects.equals(interestRate, that.interestRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), principal, tenureYears, interestRate);
    }
}
