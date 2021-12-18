package com.example.ledgerco.loan;

import com.example.ledgerco.command.BalanceCommand;
import com.example.ledgerco.command.LoanCommand;
import com.example.ledgerco.command.PaymentCommand;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Mockito.*;

class LoanServiceTest {

    LoanRepository loanRepository = mock(LoanRepository.class);
    LoanService loanService = new LoanService(loanRepository);

    @Test
    void shouldHandleLoanCommandAndSaveLoan() {
        LoanCommand loanCommand = new LoanCommand("IDIDI", "Dale", 10000, 1, 7.0);
        loanService.handleCommand(loanCommand);

        Loan expectedLoan = new Loan("IDIDI", "Dale", 10000, 1, 7.0);
        verify(loanRepository, times(1)).save(expectedLoan);
    }

    @Test
    void shouldHandleBalanceCommand() {
        when(loanRepository.findById(any())).thenReturn(Optional.of(new Loan("IDIDI", "Dale", 10000, 1, 7.0)));
        BalanceCommand balanceCommand = new BalanceCommand("IDIDI", "Dale", 1);
        loanService.handleCommand(balanceCommand);
        verify(loanRepository, times(1)).findById(new LoanIdentifier("IDIDI", "Dale"));
    }

    @Test
    void shouldHandlePaymentCommand() {
        Loan expectedLoan = new Loan("IDIDI", "Shelly", 10000, 1, 7.0);
        Mockito.when(loanRepository.findById(new LoanIdentifier("IDIDI", "Shelly"))).thenReturn(Optional.of(expectedLoan));

        PaymentCommand paymentCommand = new PaymentCommand("IDIDI", "Shelly", 1000, 2);
        loanService.handleCommand(paymentCommand);
        verify(loanRepository, times(1)).findById(new LoanIdentifier("IDIDI", "Shelly"));

        expectedLoan.acceptPayment(1000, 2);
        verify(loanRepository, times(1)).save(expectedLoan);
    }

}