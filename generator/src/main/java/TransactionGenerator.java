import com.fasterxml.jackson.databind.ObjectMapper;
import jsons.JsonItem;
import jsons.JsonTransaction;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import parser.CsvParser;
import parser.GenerateTransactionCommand;
import utility.Tuple;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static utility.RandomDataHelper.getRandomDateTime;
import static utility.RandomDataHelper.getRandomIntWithBound;

public class TransactionGenerator {
    private static final Logger logger = LogManager.getLogger(TransactionGenerator.class);
    private final CsvParser csvParser;
    private final ArrayList<Tuple<String, Double>> rawItems;
    private final ObjectMapper objectMapper;
    @Setter
    private GenerateTransactionCommand command;

    public TransactionGenerator(GenerateTransactionCommand command){
        this.command = command;
        csvParser = new CsvParser();
        rawItems = csvParser.getItems(command.getItemsFilePath());
        objectMapper = new ObjectMapper();
    }

    public void generateTransactions() throws IOException{
        ArrayList<JsonTransaction> transactionsToSave = new ArrayList<>();
        for (int i = 1; i <= command.getEventsCount(); i++) {
            transactionsToSave.add(generateSingleTransaction(command, i ));
        }
        objectMapper.writeValue(new File(command.getOutFilePath()+ ".json"), transactionsToSave);
    }

    public JsonTransaction generateSingleTransaction(GenerateTransactionCommand gTC, int id){
        ArrayList<JsonItem> items = generateItems(gTC,
                getRandomIntWithBound(gTC.getGeneratedItemsRange().getFirst(),gTC.getGeneratedItemsRange().getSecond()
                ));
        return new JsonTransaction(id,
                getRandomDateTime(gTC.getDateRange()).toString(),
                getRandomIntWithBound(gTC.getCustomerIdRange().getFirst(),gTC.getCustomerIdRange().getSecond() ),
                items,
                items.stream().mapToDouble( x -> x.getQuantity() * x.getPrice()).sum());
    }

    private ArrayList<JsonItem> generateItems(GenerateTransactionCommand gTC, int quantity){
        ArrayList<JsonItem> items = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            int quantityOfItem = getRandomIntWithBound(gTC.getItemsQuantityRange().getFirst(),gTC.getItemsQuantityRange().getSecond() );
            int randomItem =  new Random().nextInt(rawItems.size());
            items.add(new JsonItem(rawItems.get(randomItem).getFirst(),
                    quantityOfItem,
                    rawItems.get(randomItem).getSecond()));
        }
        return items;
    }

}

