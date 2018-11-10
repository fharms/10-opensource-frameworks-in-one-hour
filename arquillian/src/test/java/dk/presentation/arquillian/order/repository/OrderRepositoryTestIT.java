package dk.presentation.arquillian.order.repository;

import dk.presentation.arquillian.ArquillianIntegrationTest;
import dk.presentation.arquillian.customer.model.Customer;
import dk.presentation.arquillian.customer.repository.CustomerRepository;
import dk.presentation.arquillian.order.model.Order;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class OrderRepositoryTestIT extends ArquillianIntegrationTest {

    @Inject
    OrderRepository orderRepository;

    @Inject
    CustomerRepository customerRepository;

    @Test
    public void createOrderWithCustomerData() {
        //Given
        final Customer customer = customerRepository.find(2L);
        final Customer shipmentCustomer = customerRepository.find(3L);

        final Order expectedOrder = Order.builder()
            .orderId(1L)
            .billingAddress(customer)
            .shipmentAddress(shipmentCustomer).build();

        //When
        final Order newOrder = orderRepository.placeOrder(expectedOrder);

        //Then
        final Order actualOrder = orderRepository.findEager(newOrder.getOrderId());
        assertEquals(expectedOrder.getBillingAddress(), actualOrder.getBillingAddress());
        assertEquals(expectedOrder.getShipmentAddress(), actualOrder.getShipmentAddress());
    }

}