package com.example.ledgerco.loan;

import java.util.Objects;

public class LoanIdentifier {

    private final String bankName;
    private final String borrowerName;

    LoanIdentifier(String bankName, String borrowerName){
        this.bankName = bankName;
        this.borrowerName = borrowerName;
    }

    public String getBankName() {
        return bankName;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoanIdentifier)) return false;
        LoanIdentifier that = (LoanIdentifier) o;
        return Objects.equals(bankName, that.bankName)
                && Objects.equals(borrowerName, that.borrowerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankName, borrowerName);
    }
}
