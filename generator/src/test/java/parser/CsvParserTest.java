package parser;

import org.apache.commons.csv.CSVParser;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class CsvParserTest {

    @Test
    void getItems() throws IOException{
        // given
        // when
        // then
        CsvParser csvParser = new CsvParser();
        csvParser.getItems("items.csv");
    }
}