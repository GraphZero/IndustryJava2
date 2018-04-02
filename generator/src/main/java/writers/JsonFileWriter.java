package writers;

import com.fasterxml.jackson.databind.ObjectMapper;
import generators.JsonTransaction;
import generators.TransactionGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class JsonFileWriter{
    private static final Logger logger = LogManager.getLogger(TransactionGenerator.class);
    private final ObjectMapper objectMapper;

    public JsonFileWriter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void writeValue(String filePath, ArrayList<JsonTransaction> transactionsToSave){
        try {
            objectMapper.writeValue(new File(filePath+ ".json"), transactionsToSave);
            logger.info("Saved transactions.");
        } catch (IOException e) {
            logger.error("Couldnt map transactions...");
            e.printStackTrace();
        }
    }

}
