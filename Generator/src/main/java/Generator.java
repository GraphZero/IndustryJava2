import org.apache.commons.cli.*;
import parser.CommandsHandler;

public class Generator {

    public static void main(String[] args) throws ParseException{
        for ( String x : args ) System.out.println(x);
        CommandsHandler commandsHandler = new CommandsHandler(args);
    }
}
