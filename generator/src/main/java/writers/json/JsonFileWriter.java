package writers.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import generators.Transaction;
import generators.TransactionGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import writers.IFileWriter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class JsonFileWriter implements IFileWriter {
    private static final Logger logger = LogManager.getLogger(TransactionGenerator.class);
    private final ObjectMapper objectMapper;

    public JsonFileWriter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void writeValue(String filePath, ArrayList<Transaction> transactionsToSave){
        try {
            objectMapper.writeValue(new File(filePath+ ".json"), transactionsToSave);
            logger.info("Saved transactions.");
        } catch (IOException e) {
            logger.error("Couldnt map transactions...");
            e.printStackTrace();
        }
    }

}
