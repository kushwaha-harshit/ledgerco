package com.example.ledgerco.loan;

import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;

public class Loan {

    private final LoanIdentifier loanIdentifier;
    private final Integer principal;
    private final Double interestRate;
    private final Integer emi;
    private final Integer tenureYears;
    private final TreeMap<Integer, Integer> lumpSumPaid;

    public Loan(String bankName, String borrowerName, Integer principal, Integer tenureYears, Double interestRate) {
        this.loanIdentifier = new LoanIdentifier(bankName, borrowerName);
        this.interestRate = interestRate;
        this.principal = principal;
        this.tenureYears = tenureYears;
        this.lumpSumPaid = new TreeMap<>();
        this.emi = calculateInitialEmi();
    }

    public LoanIdentifier getLoanIdentifier() {
        return loanIdentifier;
    }

    public Integer getNumberOfRemainingEmi(Integer emiNumber) {
        Integer effectivePrincipal = effectivePrincipal(emiNumber);
        return Math.max((int) Math.ceil(effectivePrincipal / Double.valueOf(emi)), 0);
    }

    public Integer getAmountPaidTillEmiNumber(Integer emiNumber) {
        return (emi * emiNumber) + lumpSumPaidTillMonth(emiNumber);
    }

    public void acceptPayment(Integer lumpSumAmount, Integer emiMonth) {
        Optional<Integer> existingLumpSumPaidForMonth = Optional.ofNullable(lumpSumPaid.get(emiMonth));
        existingLumpSumPaidForMonth.map(amount ->
                        lumpSumPaid.put(emiMonth, amount + lumpSumAmount))
                .orElseGet(
                        () -> lumpSumPaid.put(emiMonth, lumpSumAmount)
                );
    }

    private Integer lumpSumPaidTillMonth(Integer emiMonth) {
        return lumpSumPaid.headMap(emiMonth, true).values().stream().reduce(0, Integer::sum);
    }

    private Integer calculateInitialEmi() {
        return (int) Math.ceil((principal + calculateInterest(principal)) / (tenureYears * 12));
    }

    private Double calculateInterest(Integer effectivePrincipal) {
        return Math.ceil(effectivePrincipal * tenureYears * interestRate / 100.0);
    }

    private Integer effectivePrincipal(Integer emiNumber) {
        Integer totalEmiAmount = emi * tenureYears * 12;
        Integer paidEmiAmount = emi * emiNumber;
        return totalEmiAmount - paidEmiAmount - lumpSumPaidTillMonth(emiNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Loan)) return false;
        Loan loan = (Loan) o;
        return Objects.equals(loanIdentifier, loan.loanIdentifier)
                && Objects.equals(principal, loan.principal)
                && Objects.equals(interestRate, loan.interestRate)
                && Objects.equals(emi, loan.emi)
                && Objects.equals(tenureYears, loan.tenureYears)
                && Objects.equals(lumpSumPaid, loan.lumpSumPaid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanIdentifier, principal, interestRate, emi, tenureYears, lumpSumPaid);
    }
}
