package uj.jwzp.w2.e3;

import uj.jwzp.w2.e3.external.PersistenceLayer;

import java.math.BigDecimal;

public class SellingService {

    private final PersistenceLayer persistenceLayer;
    private final DiscountConfigWrapper discountConfigWrapper;
    final CustomerMoneyService moneyService;

    public SellingService(PersistenceLayer persistenceLayer, DiscountConfigWrapper discountConfigWrapper, CustomerMoneyService moneyService) {
        this.persistenceLayer = persistenceLayer;
        this.discountConfigWrapper = discountConfigWrapper;
        this.persistenceLayer.loadDiscountConfiguration();
        this.moneyService = moneyService;
    }

    public boolean sell(Item item, int quantity, Customer customer) {
        BigDecimal money = moneyService.getMoney(customer);
        BigDecimal price = calculatePriceOfItemForUser(item, quantity, customer);
        boolean isItemDiscounted = isItemDiscounted(price);
        if (isItemDiscounted) updatePrice(price);
        boolean sold = moneyService.pay(customer, price);
        if (sold) {
            return persistenceLayer.saveTransaction(customer, item, quantity);
        } else {
            return sold;
        }
    }

    private BigDecimal calculatePriceOfItemForUser(Item item, int quantity, Customer customer) {
        return item.getPrice().subtract(discountConfigWrapper.getDiscountForItem(item, customer)).multiply(BigDecimal.valueOf(quantity));
    }

    public boolean isItemDiscounted(BigDecimal price) {
        return (discountConfigWrapper.isWeekendPromotion() && price.compareTo(BigDecimal.valueOf(5)) > 0);
    }

    protected void updatePrice(BigDecimal price){
        price = price.subtract(BigDecimal.valueOf(3));
    }

}
