package parser;

import org.junit.jupiter.api.Test;
import utility.Tuple;

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