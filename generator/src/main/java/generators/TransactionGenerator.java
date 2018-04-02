package generators;

import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import readers.CsvFileReader;
import utility.Tuple;
import writers.JsonFileWriter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static generators.TransactionGenerator.RandomDataHelper.getRandomDateTime;
import static generators.TransactionGenerator.RandomDataHelper.getRandomIntWithBound;

public class TransactionGenerator {
    private static final Logger logger = LogManager.getLogger(TransactionGenerator.class);
    private final List<Tuple<String, Double>> rawItems;
    private final JsonFileWriter jsonFileWriter;
    @Setter private GenerateTransactionCommand command;

    public TransactionGenerator(CsvFileReader csvFileReader, JsonFileWriter jsonFileWriter, GenerateTransactionCommand command) {
        this.jsonFileWriter = jsonFileWriter;
        this.command = command;
        this.rawItems = csvFileReader.getItems(command.getItemsFilePath());
    }

    public void generateTransactions() {
        ArrayList<JsonTransaction> transactionsToSave = new ArrayList<>();
        for (int i = 1; i <= command.getEventsCount(); i++) {
            transactionsToSave.add(generateSingleTransaction(command, i));
        }
        jsonFileWriter.writeValue(command.getOutFilePath(), transactionsToSave);
    }

    public JsonTransaction generateSingleTransaction(GenerateTransactionCommand command, int id) {
        ArrayList<JsonItem> items =
                generateItems(command, getRandomIntWithBound(command.getGeneratedItemsRange().getFirst(), command.getGeneratedItemsRange().getSecond()));
        return new JsonTransaction(id,
                getRandomDateTime(command.getDateRange()).toString(),
                getRandomIntWithBound(command.getCustomerIdRange().getFirst(), command.getCustomerIdRange().getSecond()),
                items,
                items.stream().mapToDouble(x -> x.getQuantity() * x.getPrice()).sum());
    }

    private ArrayList<JsonItem> generateItems(GenerateTransactionCommand command, int quantity) {
        ArrayList<JsonItem> items = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            int quantityOfItem = getRandomIntWithBound(command.getItemsQuantityRange().getFirst(), command.getItemsQuantityRange().getSecond());
            int randomItem = new Random().nextInt(rawItems.size());
            items.add(new JsonItem(rawItems.get(randomItem).getFirst(),
                    quantityOfItem,
                    rawItems.get(randomItem).getSecond()));
        }
        return items;
    }

    static class RandomDataHelper {
        private static final Random r = new Random();

        public static int getRandomIntWithBound(int lower, int upper) {
            return r.nextInt(upper - lower) + lower;
        }

        public static LocalDateTime getRandomDateTime(Tuple<LocalDateTime, LocalDateTime> ldtt) {
            return LocalDateTime.now();
        }

    }

}

