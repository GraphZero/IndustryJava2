package writers.json;

import generators.Item;
import lombok.AllArgsConstructor;

public class JsonItem extends Item {

    public JsonItem(String name, long quantity, double price) {
        super(name, quantity, price);
    }
}
