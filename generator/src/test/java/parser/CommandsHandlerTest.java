package parser;

import org.apache.commons.cli.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandsHandlerTest {
    private CommandsHandler commandsHandler;


    @BeforeEach
    public void setUpCommandsHandler() throws ParseException{
        String[] args = {
                "-customerIds", "1:20",
                "-dateRange", "2018-03-08T00:00:00.000-0100:2018-03-08T23:59:59.999-0100",
                "-itemsFile", "items.csv",
                "-itemsCount", "5:15",
                "-itemsQuantity", "1:30",
                "-eventsCount", "1000",
                "-outDir", "./output",
        };
        commandsHandler = new CommandsHandler(args);
    }

    @Test
    void shouldParse() {
        // given
        // when
        // then
    }
}