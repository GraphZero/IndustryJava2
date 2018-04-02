package writers.xml;

import generators.Item;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class XmlItem extends Item {

    public XmlItem(String name, long quantity, double price) {
        super(name, quantity, price);
    }
}
