package jsons;

import lombok.Value;

@Value
public class JsonItem {
    private String name;
    private long quantity;
    private double price;
}
