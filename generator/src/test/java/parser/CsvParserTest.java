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
import static org.junit.jupiter.api.Assertions.assertThrows;

class CsvParserTest {
    private final String fileName = "testItems.csv";

    @Test
    void shouldReturnItemsFromCsvFileInResourcesFolder(){
        // given
        CsvParser csvParser = new CsvParser();
        // when
        ArrayList<Tuple<String, Double>> items = csvParser.getItems(fileName);
        // then
        assertEquals(3, items.size());
    }

    @Test
    void shouldntFindFile(){
        // given
        CsvParser csvParser = new CsvParser();
        // when
        // then
        assertThrows(CsvParser.InputItemFileNotFoundException.class, () -> csvParser.getItems("asdasdasd"));
    }

}