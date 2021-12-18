package com.example.ledgerco.loan;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LoanRepository {

    private final Map<LoanIdentifier, Loan> loans;

    public LoanRepository(){
        loans = new HashMap<>();
    }

    public void save(Loan newLoan) {
        loans.put(newLoan.getLoanIdentifier(), newLoan);
    }

    public Optional<Loan> findById(LoanIdentifier loanIdentifier) {
        return Optional.ofNullable(loans.get(loanIdentifier));
    }
}
