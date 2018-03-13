package jsons;
import lombok.Value;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Value
public class JsonTransaction {
    private long id;
    private LocalDateTime timeStamp;
    private long customer_id;
    private ArrayList<JsonItem> jsonItems;
    private double sum;
}
