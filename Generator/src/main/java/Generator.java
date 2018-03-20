import org.apache.commons.cli.*;
import parser.CommandsHandler;
import parser.CsvParser;
import parser.GenerateTransactionCommand;
import utility.StringCommandToTransactionCommandConverter;

import java.io.IOException;

public class Generator {

    public static void main(String[] args) throws ParseException{
        CommandsHandler commandsHandler = new CommandsHandler(args);
        new StringCommandToTransactionCommandConverter()
                .convert(commandsHandler.getCmd())
                .ifPresent( generateTransactionCommand-> {
                    try {
                        TransactionGenerator transactionGenerator = new TransactionGenerator(generateTransactionCommand);
                        transactionGenerator.generateTransactions();
                    } catch (IOException e) {
                        System.out.println("Couldnt generate transactions" + e.getMessage());
                    }
                    catch (CsvParser.InputItemFileNotFoundException e) {
                        System.out.println("Couldnt find input file" );
                    }
                });
    }


}
