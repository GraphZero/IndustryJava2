package utility;

import java.time.LocalDateTime;
import java.util.Random;

public class RandomDataHelper {
    private static final Random r = new Random();

    public static int getRandomIntWithBound(int lower, int upper){
        return r.nextInt(upper - lower) + lower;
    }

    public static LocalDateTime getRandomDateTime(Tuple<LocalDateTime, LocalDateTime> ldtt){
//        int year = r.nextInt(Math.abs(ldtt.getSecond().getYear() - ldtt.getFirst().getYear() + 1)) + ldtt.getFirst().getYear() - 1;
//        int month = r.nextInt(Math.abs(ldtt.getSecond().getMonthValue() - ldtt.getFirst().getMonthValue())+ 1) + ldtt.getFirst().getMonthValue()- 1;
//        int day = r.nextInt(Math.abs(ldtt.getSecond().getDayOfMonth() - ldtt.getFirst().getDayOfMonth()+ 1)) + ldtt.getFirst().getDayOfMonth()- 1;
//        int hour = r.nextInt(Math.abs(ldtt.getSecond().getHour() - ldtt.getFirst().getHour()+ 1)) + ldtt.getFirst().getHour()- 1;
//        int minutes = r.nextInt(Math.abs(ldtt.getSecond().getMinute() - ldtt.getFirst().getMinute())) + ldtt.getFirst().getMinute();
            return LocalDateTime.now();
        }

}
