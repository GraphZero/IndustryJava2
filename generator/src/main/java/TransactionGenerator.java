import com.fasterxml.jackson.databind.ObjectMapper;
import jsons.JsonItem;
import jsons.JsonTransaction;
import parser.CsvParser;
import parser.GenerateTransactionCommand;
import utility.RandomHelper;
import utility.Tuple;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

public class TransactionGenerator {

    private final CsvParser csvParser = new CsvParser();
    private ArrayList<Tuple<String, Double>> rawItems;

    public void generateTransactions(GenerateTransactionCommand gTC) throws IOException{
        ArrayList<JsonTransaction> transactionsToSave = new ArrayList<>();
        rawItems = csvParser.getItems(gTC.getItemsFilePath());
        for (int i = 1; i <= gTC.getEventsCount(); i++) {
            transactionsToSave.add(generateSingleTransaction(gTC, i ));
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(gTC.getOutFilePath()+ ".json"), transactionsToSave);
    }

    public JsonTransaction generateSingleTransaction(GenerateTransactionCommand gTC, int id){
        int randomCustomerId = RandomHelper.getRandomIntWithBound(gTC.getCustomerIdRange().getFirst(),gTC.getCustomerIdRange().getSecond() );
        LocalDateTime randomDate = RandomHelper.getRandomDateTime(gTC.getDateRange());
        int numberOfItems = RandomHelper.getRandomIntWithBound(gTC.getGeneratedItemsRange().getFirst(),gTC.getGeneratedItemsRange().getSecond() );
        ArrayList<JsonItem> items = generateItems(gTC, numberOfItems);
        return new JsonTransaction(id,
                randomDate.toString(),
                randomCustomerId,
                items,
                items.stream().mapToDouble( x -> x.getQuantity() * x.getPrice()).sum());


    }

    protected ArrayList<JsonItem> generateItems(GenerateTransactionCommand gTC, int quantity){
        ArrayList<JsonItem> items = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            int quantityOfItem = RandomHelper.getRandomIntWithBound(gTC.getItemsQuantityRange().getFirst(),gTC.getItemsQuantityRange().getSecond() );
            int randomItem =  new Random().nextInt(rawItems.size());
            items.add(new JsonItem(rawItems.get(randomItem).getFirst(),
                    quantityOfItem,
                    rawItems.get(randomItem).getSecond()));
        }
        return items;
    }

}

