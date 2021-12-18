package com.example.ledgerco.loan;

import com.example.ledgerco.command.BalanceCommand;
import com.example.ledgerco.command.Command;
import com.example.ledgerco.command.LoanCommand;
import com.example.ledgerco.command.PaymentCommand;

import java.util.Optional;

public class LoanService {

    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public void handleCommand(Command command) {
        if (command instanceof LoanCommand) {
            executeLoanCommand((LoanCommand) command);
        } else if (command instanceof PaymentCommand) {
            executePaymentCommand((PaymentCommand) command);
        } else {
            executeBalanceCommand((BalanceCommand) command);
        }
    }

    private void executeLoanCommand(LoanCommand loanCommand) {
        Loan newLoan = new Loan(
                loanCommand.getBankName(),
                loanCommand.getBorrowerName(),
                loanCommand.getPrincipal(),
                loanCommand.getTenureYears(),
                loanCommand.getInterestRate()
        );
        loanRepository.save(newLoan);
    }

    private void executeBalanceCommand(BalanceCommand balanceCommand) {
        fetchActiveLoan(balanceCommand).ifPresent(
                activeLoan -> printLoanBalance(activeLoan, balanceCommand.getEmiNumber())
        );
    }

    private void executePaymentCommand(PaymentCommand paymentCommand) {
        fetchActiveLoan(paymentCommand).ifPresent(activeLoan -> {
            activeLoan.acceptPayment(paymentCommand.getLumpSumAmount(), paymentCommand.getEmiNumber());
            loanRepository.save(activeLoan);
        });
    }

    private String createLoanPreviewFor(Loan activeLoan, Integer emiNumber) {
        return activeLoan.getLoanIdentifier().getBankName() + " "
                + activeLoan.getLoanIdentifier().getBorrowerName() + " "
                + activeLoan.getAmountPaidTillEmiNumber(emiNumber) + " "
                + activeLoan.getNumberOfRemainingEmi(emiNumber);
    }

    private Optional<Loan> fetchActiveLoan(Command command) {
        return loanRepository.findById(
                new LoanIdentifier(command.getBankName(), command.getBorrowerName())
        );
    }

    private void printLoanBalance(Loan activeLoan, Integer emiNumber) {
        String output = createLoanPreviewFor(activeLoan, emiNumber);
        System.out.println(output);
    }
}
