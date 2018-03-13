import parser.GenerateTransactionCommand;
import utility.Tuple;

import java.time.LocalDateTime;
import java.util.Random;

public class TransactionGenerator {
    private final Random r = new Random();

    public void generateTransactions(GenerateTransactionCommand gTC){

        for (int i = 0; i < gTC.getEventsCount(); i++) {
            int randomId = getRandomIntWithBound(gTC.getCustomerIdRange().getFirst(),gTC.getCustomerIdRange().getSecond() );
            LocalDateTime randomDate = getRandomDateTime(gTC.getDateRange());
            int numberOfItems = getRandomIntWithBound(gTC.getGeneratedItemsRange().getFirst(),gTC.getGeneratedItemsRange().getSecond() );
            int quantityOfItem = getRandomIntWithBound(gTC.getItemsQuantityRange().getFirst(),gTC.getItemsQuantityRange().getSecond() );

        }
    }

    protected int getRandomIntWithBound(int lower, int upper){
        return r.nextInt(upper - lower) + lower;
    }

    protected LocalDateTime getRandomDateTime(Tuple<LocalDateTime, LocalDateTime> ldtt){
        int year = r.nextInt(ldtt.getSecond().getYear() - ldtt.getFirst().getYear()) + ldtt.getFirst().getYear();
        int month = r.nextInt(ldtt.getSecond().getMonthValue() - ldtt.getFirst().getMonthValue()) + ldtt.getFirst().getMonthValue();
        int day = r.nextInt(ldtt.getSecond().getDayOfYear() - ldtt.getFirst().getDayOfYear()) + ldtt.getFirst().getDayOfYear();
        int hour = r.nextInt(ldtt.getSecond().getHour() - ldtt.getFirst().getHour()) + ldtt.getFirst().getHour();
        int minutes = r.nextInt(ldtt.getSecond().getMinute() - ldtt.getFirst().getMinute()) + ldtt.getFirst().getMinute();
        return LocalDateTime.of(year, month, day, hour, minutes);
    }

}
