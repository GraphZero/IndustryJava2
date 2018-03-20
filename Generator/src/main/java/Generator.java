import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import parser.CommandsHandler;
import parser.CsvParser;
import parser.GenerateTransactionCommand;
import utility.StringCommandToTransactionCommandConverter;

import java.io.IOException;

public class Generator {
    private static final Logger logger = LogManager.getLogger(Generator.class);

    public static void main(String[] args) throws ParseException{

        CommandsHandler commandsHandler = new CommandsHandler(args);
        new StringCommandToTransactionCommandConverter()
                .convert(commandsHandler.getCmd())
                .ifPresent( generateTransactionCommand-> {
                    try {
                        TransactionGenerator transactionGenerator = new TransactionGenerator(generateTransactionCommand);
                        transactionGenerator.generateTransactions();
                    } catch (IOException e) {
                        logger.error("Couldnt generate transactions");
                    }
                    catch (CsvParser.InputItemFileNotFoundException e) {
                        logger.error("Couldnt find input file");
                    }
                });
    }


}
