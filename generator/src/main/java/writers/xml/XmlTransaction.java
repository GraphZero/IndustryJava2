package writers.xml;

import generators.Transaction;
import writers.json.JsonItem;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement
public class XmlTransaction extends Transaction {
    public XmlTransaction(long id, String timeStamp, long customer_id, ArrayList<JsonItem> jsonItems, double sum) {
        super(id, timeStamp, customer_id, jsonItems, sum);
    }
}
