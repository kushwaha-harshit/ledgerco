package com.example.ledgerco.command;

import org.junit.jupiter.api.Test;

class BalanceCommandTest {

    BalanceCommand balanceCommand = new BalanceCommand("Bank", "Peter", 1);

    @Test
    void shouldReturnEmiNumber(){
        assert balanceCommand.getEmiNumber() == 1;
    }

    @Test
    void shouldReturnBankName(){
        assert "Bank".equals(balanceCommand.getBankName());
    }

    @Test
    void shouldReturnBorrowerName(){
        assert "Peter".equals(balanceCommand.getBorrowerName());
    }

    @Test
    void shouldBeEqualToAnotherCommandWithSameValues(){
        BalanceCommand testBalanceCommand = new BalanceCommand("Bank", "Peter", 1);
        assert testBalanceCommand.equals(balanceCommand);
        assert testBalanceCommand.equals(testBalanceCommand);
        assert !balanceCommand.equals(new BalanceCommand("BankDummy", "Peter", 1));
        assert !balanceCommand.equals(new BalanceCommand("Bank", "PeterParker", 1));
        assert !balanceCommand.equals(new BalanceCommand("Bank", "Peter", 10));
    }

    @Test
    void shouldHaveSameHashcodeAsAnotherCommandWithSameValues(){
        assert new BalanceCommand("Bank", "Peter", 1).hashCode() == balanceCommand.hashCode();
    }

}