package com.example.ledgerco.command;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import com.example.ledgerco.utils.CustomFileReader;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;

class CommandsReaderTest {

    @Test
    void shouldProvideCommandsReadFromFile() {
        try(MockedStatic<CustomFileReader> mockedFileReader = mockStatic(CustomFileReader.class)){
            mockedFileReader.when(() -> CustomFileReader.readLines(any())).thenReturn(mockedFileReaderResponse);
            List<Command> readCommands = CommandsReader.readFromFile("dummyFilePath");
            assert readCommands != null;
            assertArrayEquals(expectedReadCommands.toArray(), readCommands.toArray());
        }
    }

    @Test
    void shouldIgnoreIncorrectCommandsReadFromFile() {
        try(MockedStatic<CustomFileReader> mockedFileReader = mockStatic(CustomFileReader.class)){
            mockedFileReader.when(() -> CustomFileReader.readLines(any())).thenReturn(mockedIncorrectFileReaderResponse);
            List<Command> readCommands = CommandsReader.readFromFile("dummyFilePath");
            assert readCommands != null;
            assertArrayEquals(expectedReadIncorrectCommands.toArray(), readCommands.toArray());
        }
    }

    private static final List<String> mockedFileReaderResponse = Arrays.asList(
            "LOAN UON Shelly 15000 2 9",
            "PAYMENT UON Shelly 7000 12",
            "BALANCE UON Shelly 12"
    );

    private static final List<String> mockedIncorrectFileReaderResponse = Arrays.asList(
            "LOAN UON Shelly 15000 2 9",
            "RANDOM_COMMAND UON Shelly 12",
            "PAYMENT UON Shelly 7000 12"
    );

    private static final List<Command> expectedReadCommands = Arrays.asList(
            new LoanCommand("UON", "Shelly", 15000, 2, 9.0),
            new PaymentCommand("UON", "Shelly",  7000, 12),
            new BalanceCommand("UON", "Shelly", 12)
    );

    private static final List<Command> expectedReadIncorrectCommands = Arrays.asList(
            new LoanCommand("UON", "Shelly", 15000, 2, 9.0),
            new PaymentCommand("UON", "Shelly",  7000, 12)
    );
}