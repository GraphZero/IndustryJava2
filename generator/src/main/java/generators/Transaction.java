package generators;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import writers.json.JsonItem;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Transaction {
    protected long id;
    protected String timeStamp;
    protected long customer_id;
    protected ArrayList<JsonItem> jsonItems;
    protected double sum;
}
