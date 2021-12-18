package com.example.ledgerco;

import com.example.ledgerco.command.Command;
import com.example.ledgerco.command.CommandsReader;
import com.example.ledgerco.ledger.Ledger;

import java.util.List;

public class Geektrust {

    public static void main(String[] args)  {
        String filePath = args[0];

        List<Command> commands = CommandsReader.readFromFile(filePath);

        Ledger ledger = Ledger.create();
        ledger.executeCommands(commands);
    }

}
