package generators;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Item {
    protected String name;
    protected long quantity;
    protected double price;
}
