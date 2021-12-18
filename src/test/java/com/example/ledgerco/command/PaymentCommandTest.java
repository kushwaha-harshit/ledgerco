package com.example.ledgerco.command;

import org.junit.jupiter.api.Test;

class PaymentCommandTest {
    
    PaymentCommand paymentCommand = new PaymentCommand("Bank", "Peter", 1000, 1);

    @Test
    void shouldReturnLumpSumAmount(){
        assert paymentCommand.getLumpSumAmount() == 1000;
    }

    @Test
    void shouldReturnTEmiNumber(){
        assert paymentCommand.getEmiNumber() == 1;
    }

    @Test
    void shouldReturnBankName(){
        assert "Bank".equals(paymentCommand.getBankName());
    }

    @Test
    void shouldReturnBorrowerName(){
        assert "Peter".equals(paymentCommand.getBorrowerName());
    }

    @Test
    void shouldBeEqualToAnotherCommandWithSameValues(){
        PaymentCommand testPaymentCommand = new PaymentCommand("Bank", "Peter", 1000, 1);
        assert testPaymentCommand.equals(paymentCommand);
        assert testPaymentCommand.equals(paymentCommand);
        assert !paymentCommand.equals(new PaymentCommand("Banks", "Peter", 1000, 1));
        assert !paymentCommand.equals(new PaymentCommand("Bank", "Peter Parker", 1000, 1));
        assert !paymentCommand.equals(new PaymentCommand("Bank", "Peter", 10000, 1));
        assert !paymentCommand.equals(new PaymentCommand("Bank", "Peter", 1000, 10));
    }

    @Test
    void shouldHaveSameHashcodeAsAnotherCommandWithSameValues(){
        assert new PaymentCommand("Bank", "Peter", 1000, 1).hashCode() == paymentCommand.hashCode();
    }
    
}