package parser;
import lombok.Getter;
import org.apache.commons.cli.*;

import java.util.Collection;

@Getter
public class CommandsHandler {
    private final Options options = new Options();
    private final CommandLineParser parser = new DefaultParser();
    private CommandLine cmd;

    public CommandsHandler(String[] args) throws ParseException {
        options.addOption("customerIds", true, "gets customer id");
        options.addOption("dateRange", true, "date range");
        options.addOption("itemsFile", true, "item source file");
        options.addOption("itemsCount", true, "number of items generated ");
        options.addOption("itemsQuantity", true, "quantity of item");
        options.addOption("eventsCount", true, "number of transactions");
        options.addOption("outDir", true, "destination file");
        cmd = parser.parse( options, args);
    }

}
