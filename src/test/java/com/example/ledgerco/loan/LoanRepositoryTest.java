package com.example.ledgerco.loan;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class LoanRepositoryTest {

    @Test
    void shouldSaveLoan() {
        assertDoesNotThrow(() -> {
            LoanRepository loanRepository = new LoanRepository();
            loanRepository.save(new Loan("IDIDI", "Dale", 10000, 1, 7.0));
        });
    }

    @Test
    void shouldFindLoanByIdentifierWhenPresent() {
        LoanRepository loanRepository = new LoanRepository();
        Loan loan = new Loan("IDIDI", "Dale", 10000, 1, 7.0);
        loanRepository.save(loan);
        Optional<Loan> fetchedOptionalLoan = loanRepository.findById(loan.getLoanIdentifier());
        assert fetchedOptionalLoan.isPresent();
        assertEquals(loan, fetchedOptionalLoan.get());
    }

    @Test
    void shouldNotReturnLoanByIdentifierWhenNotPresent() {
        LoanRepository loanRepository = new LoanRepository();
        Optional<Loan> fetchedOptionalLoan = loanRepository.findById(new LoanIdentifier("dummyBank", "dummyBorrower"));
        assert !fetchedOptionalLoan.isPresent();
    }
}