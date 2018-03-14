package parser;

import jsons.JsonItem;
import org.apache.commons.csv.CSVParser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.Tuple;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CsvParserTest {
    private final String fileName = "testItems.csv";

    @Test
    void shouldReturnItemsFromCsvFileInResourcesFolder() throws IOException{
        // given
        CsvParser csvParser = new CsvParser();
        // when
        ArrayList<Tuple<String, Double>> items = csvParser.getItems(fileName);
        // then
        assertEquals(3, items.size());
    }
}