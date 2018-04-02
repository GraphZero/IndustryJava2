import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import parser.CommandsHandler;
import parser.CsvParser;
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
                        logger.info("Successfully generated transactions into " + generateTransactionCommand.getOutFilePath());
                    } catch (IOException e) {
                        logger.error("Couldn't generate transactions");
                    } catch (CsvParser.InputItemFileNotFoundException e) {
                        logger.error("Couldn't find input file");
                    }
                });
    }


}
