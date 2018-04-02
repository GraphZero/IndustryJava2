package writers.json;

import generators.Transaction;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.ArrayList;

public class JsonTransaction extends Transaction {

    public JsonTransaction(long id, String timeStamp, long customer_id, ArrayList<JsonItem> jsonItems, double sum) {
        super(id, timeStamp, customer_id, jsonItems, sum);
    }
}
