import org.apache.commons.cli.*;
import parser.CommandsHandler;
import parser.GenerateTransactionCommand;
import utility.StringCommandToTransactionCommandConverter;

import java.io.IOException;

public class Generator {

    public static void main(String[] args) throws ParseException{
        CommandsHandler commandsHandler = new CommandsHandler(args);
        GenerateTransactionCommand generateTransactionCommand
                = StringCommandToTransactionCommandConverter.convert(commandsHandler.getCmd());
        TransactionGenerator transactionGenerator = new TransactionGenerator();
        try {
            transactionGenerator.generateTransactions(generateTransactionCommand);
        } catch (IOException e) {
            System.out.println("Couldnt generate transactions" + e.getMessage());
        }

    }
}
