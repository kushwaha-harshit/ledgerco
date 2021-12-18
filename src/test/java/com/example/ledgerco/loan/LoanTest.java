package com.example.ledgerco.loan;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoanTest {

    @Test
    void shouldReturnRemainingNumberOfEMIs() {
        Loan loan = new Loan("IDIDI", "Dale", 10000, 5, 4.0);
        Integer numberOfRemainingEmi = loan.getNumberOfRemainingEmi(5);
        assertNotNull(numberOfRemainingEmi);
        assert numberOfRemainingEmi == 55;
    }

    @Test
    void shouldReturnRemainingNumberOfEMIsWhenPaymentMadeInSameMonth() {
        Loan loan = new Loan("UON", " Shelly",  15000, 2, 9.0);
        loan.acceptPayment(7000, 12);
        Integer numberOfRemainingEmi = loan.getNumberOfRemainingEmi(12);
        assertNotNull(numberOfRemainingEmi);
        assert numberOfRemainingEmi == 3;
    }

    @Test
    void shouldReturnPaidAmount() {
        Loan loan = new Loan("IDIDI", "Dale", 10000, 5, 4.0);
        Integer amountPaidSoFar = loan.getAmountPaidTillEmiNumber(5);
        assertNotNull(amountPaidSoFar);
        assert amountPaidSoFar == 1000;
    }

    @Test
    void shouldAcceptPayment() {
        Loan loan = new Loan("IDIDI", "Dale", 5000, 1, 6.0);
        loan.acceptPayment(1000, 5);

        Integer amountPaidSoFar = loan.getAmountPaidTillEmiNumber(3);
        assertNotNull(amountPaidSoFar);
        assert amountPaidSoFar == 1326;

        amountPaidSoFar = loan.getAmountPaidTillEmiNumber(6);
        assertNotNull(amountPaidSoFar);
        assert amountPaidSoFar == 3652;
    }

    @Test
    void shouldAcceptPaymentForSameMonth() {
        Loan loan = new Loan("IDIDI", "Dale", 5000, 1, 6.0);
        loan.acceptPayment(1000, 5);
        Integer amountPaidSoFar = loan.getAmountPaidTillEmiNumber(6);
        assertNotNull(amountPaidSoFar);
        assert amountPaidSoFar == 3652;

        loan.acceptPayment(1000, 5);
        amountPaidSoFar = loan.getAmountPaidTillEmiNumber(6);
        assertNotNull(amountPaidSoFar);
        assert amountPaidSoFar == 4652;
    }

    @Test
    void shouldAcceptPaymentAndDisplayCorrectBalanceForSameMonth(){
        Loan loan = new Loan("IDIDI", "Dale", 10000, 1, 2.0);
        loan.acceptPayment(1000, 5);
        Integer amountPaidSoFar = loan.getAmountPaidTillEmiNumber(5);
        assertNotNull(amountPaidSoFar);
        assert amountPaidSoFar == 5250;
    }

    @Test
    void shouldProvideLoanIdentifier(){
        Loan loan = new Loan("IDIDI", "Dale", 5000, 1, 6.0);
        LoanIdentifier fetchedIdentifier = loan.getLoanIdentifier();
        LoanIdentifier expectedIdentifier = new LoanIdentifier("IDIDI", "Dale");
        assertEquals(expectedIdentifier, fetchedIdentifier);
    }

    @Test
    void shouldBeEqualToAnotherLoanObjectWithSameValues(){
        Loan loan = new Loan("IDIDI", "Dale", 5000, 1, 6.0);
        Loan anotherLoan = new Loan("IDIDI", "Dale", 5000, 1, 6.0);
        assert loan.equals(anotherLoan);
        assert loan.equals(loan);
        assert !loan.equals(new Loan("ICICI", "Dale", 5000, 1, 6.0));
        assert !loan.equals(new Loan("IDIDI", "Dale Park", 5000, 1, 6.0));
        assert !loan.equals(new Loan("IDIDI", "Dale", 50000, 1, 6.0));
        assert !loan.equals(new Loan("IDIDI", "Dale", 5000, 10, 6.0));
        assert !loan.equals(new Loan("IDIDI", "Dale", 5000, 1, 16.0));
        anotherLoan.acceptPayment(1000, 1);
        assert !loan.equals(anotherLoan);
    }

    @Test
    void shouldHaveSameHashcodeAsAnotherLoanObjectWithSameValues(){
        Loan loan = new Loan("IDIDI", "Dale", 5000, 1, 6.0);
        Loan anotherLoan = new Loan("IDIDI", "Dale", 5000, 1, 6.0);
        assert loan.hashCode() == anotherLoan.hashCode();
    }

}