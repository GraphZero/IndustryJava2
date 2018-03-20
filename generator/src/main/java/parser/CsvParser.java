package parser;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utility.Tuple;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class CsvParser {
    private static final Logger logger = LogManager.getLogger(CsvParser.class);

    public ArrayList<Tuple<String, Double>> getItems(String path){
        ClassLoader classLoader = getClass().getClassLoader();
        Iterable<CSVRecord> records;
        Reader in;
        try {
            if ( classLoader.getResource(path) != null ){
                in = new FileReader(classLoader.getResource(path).getFile().replaceAll("%20", " "));
                try {
                    records = CSVFormat
                            .newFormat(',')
                            .withHeader("name", "price")
                            .parse(in);
                    return returnItems(records);
                } catch (IOException e) {
                    logger.error("Couldnt parse items!");
                    throw new InputParseFileException();
                }
            } else{
                logger.error("Coudlnt find item file.");
                throw new InputItemFileNotFoundException();
            }
        } catch (FileNotFoundException e) {
            logger.error("Couldnt find item file!");
            throw new InputItemFileNotFoundException();
        }
    }

    protected ArrayList<Tuple<String, Double>> returnItems(Iterable<CSVRecord> records){
        records.iterator().next();
        ArrayList<Tuple<String, Double>> items = new ArrayList<>();

        for (CSVRecord record : records) {
            items.add( new Tuple<>( record.get("name"), Double.parseDouble( record.get("price"))));
        }
        return items;
    }

    public class InputItemFileNotFoundException extends RuntimeException{ }
    public class InputParseFileException extends RuntimeException{ }

}
