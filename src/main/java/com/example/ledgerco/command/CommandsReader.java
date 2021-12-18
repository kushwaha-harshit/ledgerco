package com.example.ledgerco.command;

import com.example.ledgerco.utils.CustomFileReader;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CommandsReader {

    private CommandsReader() {}

    private static final String TEXT_SPLITTER = " ";

    public static List<Command> readFromFile(String filePath) {
        List<String> textCommands = CustomFileReader.readLines(filePath);
        return createInputCommandsFromText(textCommands);
    }

    private static List<Command> createInputCommandsFromText(List<String> textCommands) {
        return textCommands.stream()
                .map(CommandsReader::createCommand)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private static Command createCommand(String text) {
        String[] splitText = text.split(TEXT_SPLITTER);
        switch (splitText[0]) {
            case "LOAN":
                return createLoanCommand(splitText);
            case "BALANCE":
                return createBalanceCommand(splitText);
            case "PAYMENT":
                return createPaymentCommand(splitText);
            default:
                return null;
        }
    }

    private static LoanCommand createLoanCommand(String[] splitText) {
        return new LoanCommand(splitText[1], splitText[2], Integer.valueOf(splitText[3]), Integer.valueOf(splitText[4]), Double.valueOf(splitText[5]));
    }

    private static BalanceCommand createBalanceCommand(String[] splitText) {
        return new BalanceCommand(splitText[1], splitText[2], Integer.valueOf(splitText[3]));
    }

    private static PaymentCommand createPaymentCommand(String[] splitText) {
        return new PaymentCommand(splitText[1], splitText[2], Integer.valueOf(splitText[3]), Integer.valueOf(splitText[4]));
    }


}
