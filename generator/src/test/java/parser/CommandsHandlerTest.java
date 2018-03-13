package parser;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandsHandlerTest {
    private CommandsHandler commandsHandler;


    @BeforeEach
    public void setUpCommandsHandler() throws ParseException{
        String[] args = {
                "-customerIds", "1:20"
        };
        commandsHandler = new CommandsHandler(args);
    }

    @Test
    void shouldParseCommandLine() {
        // given
        CommandLine commandLine = commandsHandler.getCmd();
        // when
        // then
        assertEquals("1:20", commandLine.getOptionValue("customerIds"), "CommandsHandler didnt parse argument");
    }
}