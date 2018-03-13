package parser;

import jsons.JsonItem;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import utility.Tuple;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class CsvParser {

    public static ArrayList<Tuple<String, Double>> getItems(String path) throws IOException{
        Reader in = new FileReader(path);
        Iterable<CSVRecord> records = CSVFormat
                .newFormat(',')
                .withHeader("name", "price")
                .parse(in);
        records.iterator().next();
        ArrayList<Tuple<String, Double>> items = new ArrayList<>();

        for (CSVRecord record : records) {
            items.add( new Tuple<>( record.get("name"), Double.parseDouble( record.get("price"))));
        }
        return items;
    }

}
