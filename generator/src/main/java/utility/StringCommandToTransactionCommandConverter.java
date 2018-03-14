package utility;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.apache.commons.cli.CommandLine;
import parser.GenerateTransactionCommand;

import java.time.LocalDateTime;
import java.util.Optional;

public class StringCommandToTransactionCommandConverter {

    public static Optional<GenerateTransactionCommand> convert(CommandLine commandLine){
        GenerateTransactionCommand generateTransactionCommand = null;
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
        } catch(NumberFormatException e){
            System.out.println("Wrong parameters!");
            return Optional.empty();
        }
        return Optional.ofNullable(generateTransactionCommand);
    }

    public static Tuple<Integer, Integer> parseRange(String s){
        String[] idRange = s.trim().split(":");
        return new Tuple<>(Integer.parseInt(idRange[0]), Integer.parseInt(idRange[1]));
    }

    public static Tuple<LocalDateTime, LocalDateTime> parseDateRange(String s){
        String[] idRange = s.trim().split("-0100:");
        return new Tuple<>(LocalDateTime.parse(idRange[0] ), LocalDateTime.parse(idRange[1].replaceAll("-0100", "") ));
    }


}
