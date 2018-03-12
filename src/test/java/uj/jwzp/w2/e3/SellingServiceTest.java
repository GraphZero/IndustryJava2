package uj.jwzp.w2.e3;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import uj.jwzp.w2.e3.external.DiscountsConfig;
import uj.jwzp.w2.e3.external.PersistenceLayer;

import java.math.BigDecimal;

@RunWith(MockitoJUnitRunner.class)
public class SellingServiceTest {

    @Mock
    private static PersistenceLayer persistenceLayer;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test(expected = SellingService.ItemNotSoldException.class)
    public void notSell() {
        //given
        SellingService uut = new SellingService(persistenceLayer);
        Mockito.when(persistenceLayer.saveCustomer(Mockito.any())).thenReturn(Boolean.TRUE);
        Item i = new Item("i", new BigDecimal(3));
        Customer c = new Customer(1, "DasCustomer", "Kraków, Łojasiewicza");

        //when
        boolean sold = uut.sell(i, 7, c);

        //then
        Assert.assertFalse(sold);
        Assert.assertEquals(BigDecimal.valueOf(10), uut.moneyService.getMoney(c));
    }

    @Test
    public void sell() {
        //given
        Mockito.when(persistenceLayer.saveCustomer(Mockito.any())).thenReturn(Boolean.TRUE);
        Mockito.when(persistenceLayer.saveTransaction(Mockito.any(), Mockito.any(),Mockito.anyInt())).thenReturn(Boolean.TRUE);
        //Mockito.doReturn(Boolean.TRUE).when(persistenceLayer).saveCustomer(Mockito.any());
        SellingService uut = new SellingService(persistenceLayer);

        Item i = new Item("i", new BigDecimal(3));
        Customer c = new Customer(1, "DasCustomer", "Kraków, Łojasiewicza");

        //when
        boolean sold = uut.sell(i, 1, c);

        //then
        Assert.assertFalse(sold);
        Assert.assertEquals(BigDecimal.valueOf(7), uut.moneyService.getMoney(c));
    }

    @Test
    public void sellALot() {
        //given
        Mockito.when(persistenceLayer.saveCustomer(Mockito.any())).thenReturn(Boolean.TRUE);
        Mockito.when(persistenceLayer.saveTransaction(Mockito.any(), Mockito.any(),Mockito.anyInt())).thenReturn(Boolean.TRUE);
        SellingService uut = new SellingService(persistenceLayer);

        Item i = new Item("i", new BigDecimal(3));
        Customer c = new Customer(1, "DasCustomer", "Kraków, Łojasiewicza");
        uut.moneyService.addMoney(c, new BigDecimal(990));

        //when
        boolean isDiscounted = uut.sell(i, 10, c);

        //then
        if ( isDiscounted ){
            Assert.assertEquals(BigDecimal.valueOf(973), uut.moneyService.getMoney(c));
        } else{
            Assert.assertEquals(BigDecimal.valueOf(970), uut.moneyService.getMoney(c));
        }

    }

    @Test(expected = SellingService.TransactionNotSavedException.class)
    public void shouldntSaveTransaction() {
        //given
        Mockito.when(persistenceLayer.saveCustomer(Mockito.any())).thenReturn(Boolean.TRUE);
        SellingService uut = new SellingService(persistenceLayer);

        Item i = new Item("i", new BigDecimal(3));
        Customer c = new Customer(1, "DasCustomer", "Kraków, Łojasiewicza");
        uut.moneyService.addMoney(c, new BigDecimal(990));

        //when
        boolean isDiscounted = uut.sell(i, 10, c);

        //then
        if ( isDiscounted ){
            Assert.assertEquals(BigDecimal.valueOf(973), uut.moneyService.getMoney(c));
        } else{
            Assert.assertEquals(BigDecimal.valueOf(970), uut.moneyService.getMoney(c));
        }

    }

}
