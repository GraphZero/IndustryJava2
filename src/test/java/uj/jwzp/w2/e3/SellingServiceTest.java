package uj.jwzp.w2.e3;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import uj.jwzp.w2.e3.external.PersistenceLayer;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SellingServiceTest {

    @Mock
    private static PersistenceLayer persistenceLayer;

    @Mock
    private DiscountConfigWrapper discountConfigWrapper;

    @Mock
    private CustomerMoneyService customerMoneyService;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void shouldSell() {
        //given
        when(customerMoneyService.getMoney(Mockito.any())).thenReturn(BigDecimal.valueOf(10000));
        when(persistenceLayer.saveTransaction(Mockito.any(), Mockito.any(),Mockito.anyInt())).thenReturn(Boolean.TRUE);
        when(customerMoneyService.pay(Mockito.any(), Mockito.any())).thenReturn(Boolean.TRUE);
        when(discountConfigWrapper.getDiscountForItem(Mockito.any(), Mockito.any() )).thenReturn(BigDecimal.ZERO);
        SellingService uut = new SellingService(persistenceLayer, discountConfigWrapper, customerMoneyService);

        Item i = new Item("i", new BigDecimal(3));
        Customer c = new Customer(1, "DasCustomer", "Kraków, Łojasiewicza");

        //when
        boolean sold = uut.sell(i, 1, c);

        //then
        Assert.assertTrue(sold);
    }

    @Test
    public void shouldApplyDiscount() {
        //given
        SellingService uut = spy( new SellingService(persistenceLayer, discountConfigWrapper, customerMoneyService));
        when(customerMoneyService.getMoney(Mockito.any())).thenReturn(BigDecimal.valueOf(10));
        when(discountConfigWrapper.isWeekendPromotion()).thenReturn(Boolean.TRUE);
        when(discountConfigWrapper.getDiscountForItem(Mockito.any(), Mockito.any() )).thenReturn(BigDecimal.valueOf(1));
        Item i = new Item("i", new BigDecimal(7));
        Customer c = new Customer(1, "DasCustomer", "Kraków, Łojasiewicza");

        //when
        boolean sold = uut.sell(i, 1, c);

        //then
        verify(uut, times(1)).updatePrice(Mockito.any());
    }

    @Test
    public void shouldCorrectlyApplyDiscountToPrice() {
        //given
        SellingService uut = new SellingService(persistenceLayer, discountConfigWrapper, customerMoneyService);

        //when
        BigDecimal price = BigDecimal.TEN;
        BigDecimal updatedPrice = uut.updatePrice(price);

        //then
        assertEquals(BigDecimal.valueOf(7), updatedPrice);
    }

    @Test
    public void shouldntSellBecauseOfNotSufficentMoney() {
        //given
        SellingService uut = new SellingService(persistenceLayer, discountConfigWrapper, customerMoneyService);
        when(customerMoneyService.getMoney(Mockito.any())).thenReturn(BigDecimal.ZERO);
        when(discountConfigWrapper.getDiscountForItem(Mockito.any(), Mockito.any() )).thenReturn(BigDecimal.ZERO);
        Item i = new Item("i", new BigDecimal(3));
        Customer c = new Customer(1, "DasCustomer", "Kraków, Łojasiewicza");

        //when
        boolean sold = uut.sell(i, 7, c);

        //then
        assertFalse(sold);
    }

    @Test
    public void shouldntSellBecauseOfTransactionsError() {
        //given
        when(customerMoneyService.getMoney(Mockito.any())).thenReturn(BigDecimal.valueOf(10000));
        when(discountConfigWrapper.getDiscountForItem(Mockito.any(), Mockito.any() )).thenReturn(BigDecimal.ZERO);
        when(customerMoneyService.pay(Mockito.any(), Mockito.any())).thenReturn(Boolean.TRUE);
        when(persistenceLayer.saveTransaction(Mockito.any(), Mockito.any(),Mockito.anyInt())).thenReturn(Boolean.FALSE);
        SellingService uut = new SellingService(persistenceLayer, discountConfigWrapper, customerMoneyService);

        Item i = new Item("i", new BigDecimal(3));
        Customer c = new Customer(1, "DasCustomer", "Kraków, Łojasiewicza");
        uut.moneyService.addMoney(c, new BigDecimal(990));

        //when
        boolean isSold = uut.sell(i, 10, c);

        //then
        assertFalse(isSold);
    }

    @Test
    public void shouldntSellBecauseOfPaymentError() {
        //given
        when(customerMoneyService.getMoney(Mockito.any())).thenReturn(BigDecimal.valueOf(10000));
        when(discountConfigWrapper.getDiscountForItem(Mockito.any(), Mockito.any() )).thenReturn(BigDecimal.ZERO);
        when(customerMoneyService.pay(Mockito.any(), Mockito.any())).thenReturn(Boolean.FALSE);
        SellingService uut = new SellingService(persistenceLayer, discountConfigWrapper, customerMoneyService);

        Item i = new Item("i", new BigDecimal(3));
        Customer c = new Customer(1, "DasCustomer", "Kraków, Łojasiewicza");
        uut.moneyService.addMoney(c, new BigDecimal(990));

        //when
        boolean isSold = uut.sell(i, 10, c);

        //then
        assertFalse(isSold);
    }

    @Test
    public void shouldUpdatePrice(){
        // given
        SellingService uut = new SellingService(persistenceLayer, discountConfigWrapper, customerMoneyService);
        BigDecimal bigDecimal = BigDecimal.TEN;
        // when
        bigDecimal = uut.updatePrice(bigDecimal);
        // then
        assertEquals( BigDecimal.valueOf(7), bigDecimal);
    }

}

