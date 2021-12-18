package com.example.ledgerco.command;

import com.example.ledgerco.loan.Loan;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoanCommandTest {

    LoanCommand loanCommand = new LoanCommand("Bank", "Peter", 1000, 1, 6.0);

    @Test
    void shouldReturnPrincipalAmount(){
        assert loanCommand.getPrincipal() == 1000;
    }

    @Test
    void shouldReturnTenureInYears(){
        assert loanCommand.getTenureYears() == 1;
    }

    @Test
    void shouldReturnInterestRate(){
        assert loanCommand.getInterestRate() == 6.0;
    }

    @Test
    void shouldReturnBankName(){
        assert "Bank".equals(loanCommand.getBankName());
    }

    @Test
    void shouldReturnBorrowerName(){
        assert "Peter".equals(loanCommand.getBorrowerName());
    }

    @Test
    void shouldBeEqualToAnotherCommandWithSameValues(){
        LoanCommand testLoanCommand = new LoanCommand("Bank", "Peter", 1000, 1, 6.0);
        assert testLoanCommand.equals(loanCommand);
        assert testLoanCommand.equals(loanCommand);
        assert !loanCommand.equals(new LoanCommand("Banks", "Peter", 1000, 1, 6.0));
        assert !loanCommand.equals(new LoanCommand("Bank", "Peter Parker", 1000, 1, 6.0));
        assert !loanCommand.equals(new LoanCommand("Bank", "Peter", 10000, 1, 6.0));
        assert !loanCommand.equals(new LoanCommand("Bank", "Peter", 1000, 10, 6.0));
        assert !loanCommand.equals(new LoanCommand("Bank", "Peter", 1000, 1, 16.0));

    }

    @Test
    void shouldHaveSameHashcodeAsAnotherCommandWithSameValues(){
        assert new LoanCommand("Bank", "Peter", 1000, 1, 6.0).hashCode() == loanCommand.hashCode();
    }

}