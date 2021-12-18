package com.example.ledgerco.loan;

import org.junit.jupiter.api.Test;

class LoanIdentifierTest {

    @Test
    void shouldBeEqualToAnotherObjectWithSameValues(){
        LoanIdentifier loanIdentifier = new LoanIdentifier("Bank", "Peter");
        LoanIdentifier anotherLoanIdentifier = new LoanIdentifier("Bank", "Peter");
        assert loanIdentifier.equals(anotherLoanIdentifier);
        assert loanIdentifier.equals(loanIdentifier);
        assert !loanIdentifier.equals(new LoanIdentifier("SuperBank", "Peter"));
        assert !loanIdentifier.equals(new LoanIdentifier("Bank", "PeterParker"));
    }

}