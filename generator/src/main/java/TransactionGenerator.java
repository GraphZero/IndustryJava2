import com.fasterxml.jackson.databind.ObjectMapper;
import jsons.JsonItem;
import jsons.JsonTransaction;
import parser.CsvParser;
import parser.GenerateTransactionCommand;
import utility.Tuple;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

public class TransactionGenerator {
    private final Random r = new Random();

    public void generateTransactions(GenerateTransactionCommand gTC) throws IOException{
        ArrayList<JsonTransaction> transactionsToSave = new ArrayList<>();
        for (int i = 0; i < gTC.getEventsCount(); i++) {
            transactionsToSave.add(generateSingleTransaction(gTC, i));
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(gTC.getOutFilePath()), transactionsToSave);
    }

    public JsonTransaction generateSingleTransaction(GenerateTransactionCommand gTC, int id) throws IOException{
        int randomCustomerId = getRandomIntWithBound(gTC.getCustomerIdRange().getFirst(),gTC.getCustomerIdRange().getSecond() );
        LocalDateTime randomDate = getRandomDateTime(gTC.getDateRange());
        int numberOfItems = getRandomIntWithBound(gTC.getGeneratedItemsRange().getFirst(),gTC.getGeneratedItemsRange().getSecond() );
        ArrayList<JsonItem> items = generateItems(gTC, numberOfItems);
        return new JsonTransaction(id,
                randomDate.toString(),
                randomCustomerId,
                items,
                items.stream().mapToDouble( x -> x.getQuantity() * x.getPrice()).sum());

    }

    protected ArrayList<JsonItem> generateItems(GenerateTransactionCommand gTC, int quantity) throws IOException{
        ArrayList<JsonItem> items = new ArrayList<>();
        ArrayList<Tuple<String, Double>> rawItems = CsvParser.getItems(gTC.getItemsFilePath());
        for (int i = 0; i < quantity; i++) {
            int quantityOfItem = getRandomIntWithBound(gTC.getItemsQuantityRange().getFirst(),gTC.getItemsQuantityRange().getSecond() );
            int randomItem =  r.nextInt(rawItems.size());
            items.add(new JsonItem(rawItems.get(randomItem).getFirst(),
                    quantityOfItem,
                    rawItems.get(randomItem).getSecond()));
        }
        return items;
    }

    protected int getRandomIntWithBound(int lower, int upper){
        return r.nextInt(upper - lower) + lower;
    }

    protected LocalDateTime getRandomDateTime(Tuple<LocalDateTime, LocalDateTime> ldtt){
//        int year = r.nextInt(Math.abs(ldtt.getSecond().getYear() - ldtt.getFirst().getYear() + 1)) + ldtt.getFirst().getYear() - 1;
//        int month = r.nextInt(Math.abs(ldtt.getSecond().getMonthValue() - ldtt.getFirst().getMonthValue())+ 1) + ldtt.getFirst().getMonthValue()- 1;
//        int day = r.nextInt(Math.abs(ldtt.getSecond().getDayOfMonth() - ldtt.getFirst().getDayOfMonth()+ 1)) + ldtt.getFirst().getDayOfMonth()- 1;
//        int hour = r.nextInt(Math.abs(ldtt.getSecond().getHour() - ldtt.getFirst().getHour()+ 1)) + ldtt.getFirst().getHour()- 1;
//        int minutes = r.nextInt(Math.abs(ldtt.getSecond().getMinute() - ldtt.getFirst().getMinute())) + ldtt.getFirst().getMinute();
        return LocalDateTime.now();
    }

}
