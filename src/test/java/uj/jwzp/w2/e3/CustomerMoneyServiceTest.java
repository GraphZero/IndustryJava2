package uj.jwzp.w2.e3;

import org.junit.Before;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CustomerMoneyServiceTest{
    private CustomerMoneyService customerMoneyService;

    @Mock
    private static PersistenceLayer persistenceLayer;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setCustomerMoneyService(){
        customerMoneyService = new CustomerMoneyService(persistenceLayer);
    }

    @Test
    public void shouldSaveAbsentCustomerWhileGettingMoney(){
        // given
        Customer customer = new Customer(1, "a", "b");
        // when
        customerMoneyService.getMoney(customer);
        customerMoneyService.getMoney(customer);
        // then
        verify(persistenceLayer, times(1)).saveCustomer(Mockito.any());
    }

    @Test
    public void shouldntPayBecauseOfInsuficentMoney (){
        // given
        Customer customer = new Customer(1, "a", "b");
        // when
        boolean isPaymentSuccesfull = customerMoneyService.pay(customer, BigDecimal.valueOf(15));
        // then
        assertFalse(isPaymentSuccesfull);
    }

    @Test
    public void shouldBeAbleToPay(){
        // given
        Customer customer = new Customer(1, "a", "b");
        // when
        boolean isPaymentSuccesfull = customerMoneyService.pay(customer, BigDecimal.valueOf(10));
        // then
        assertTrue(isPaymentSuccesfull);
    }

    @Test
    public void shouldCreateUserWith10MoneyWhileGettingMoney() {
        // given
        Customer customer = new Customer(1, "a", "b");
        // when
        BigDecimal money = customerMoneyService.getMoney(customer);
        // then
        assertThat(money).isEqualTo(BigDecimal.valueOf(10));
    }



}
