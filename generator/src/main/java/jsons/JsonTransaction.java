package jsons;

import lombok.Value;

import java.util.ArrayList;

@Value
public class JsonTransaction {
    private long id;
    private String timeStamp;
    private long customer_id;
    private ArrayList<JsonItem> jsonItems;
    private double sum;
}
