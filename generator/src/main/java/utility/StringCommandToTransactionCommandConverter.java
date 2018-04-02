package utility;

import org.apache.commons.cli.CommandLine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import parser.GenerateTransactionCommand;

import java.time.LocalDateTime;
import java.util.Optional;

public class StringCommandToTransactionCommandConverter {
    private static final Logger logger = LogManager.getLogger(StringCommandToTransactionCommandConverter.class);

    public Optional<GenerateTransactionCommand> convert(CommandLine commandLine){
        GenerateTransactionCommand generateTransactionCommand;
        try{
             generateTransactionCommand =  new GenerateTransactionCommand(
                    parseRange(commandLine.getOptionValue("customerIds", "1:5")),
                    parseDateRange(commandLine.getOptionValue("dateRange", LocalDateTime.now() + "-0100:" + LocalDateTime.now().plusDays(1))),
                    parseRange(commandLine.getOptionValue("itemsCount", "1:5")),
                    parseRange(commandLine.getOptionValue("itemsQuantity", "1:5")),
                    Long.parseLong( commandLine.getOptionValue("eventsCount", "100") ),
                    commandLine.getOptionValue("itemsFile", "/"),
                    commandLine.getOptionValue("outDir", "/")
            );
            logger.info("Successfully converted command.");
        } catch(NumberFormatException e){
            logger.error("Wrong command parameters!");
            return Optional.empty();
        }
        return Optional.ofNullable(generateTransactionCommand);
    }

    public Tuple<Integer, Integer> parseRange(String s){
        String[] idRange = s.trim().split(":");
        if ( Integer.parseInt(idRange[0]) < 0 || Integer.parseInt(idRange[1]) < 0){
            throw new WrongRangeException();
        }
        return new Tuple<>(Integer.parseInt(idRange[0]), Integer.parseInt(idRange[1]));
    }

    public Tuple<LocalDateTime, LocalDateTime> parseDateRange(String s){
        String[] idRange = s.trim().split("-0100:");
        return new Tuple<>(LocalDateTime.parse(idRange[0] ), LocalDateTime.parse(idRange[1].replaceAll("-0100", "") ));
    }

    class WrongRangeException extends RuntimeException{}

}
