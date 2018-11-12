package dk.presentation.mapstruct.order.model;

import dk.presentation.mapstruct.ArquillianIntegrationTest;
import dk.presentation.mapstruct.customer.entity.CustomerEntity;
import dk.presentation.mapstruct.customer.repository.CustomerRepository;
import dk.presentation.mapstruct.order.entity.OrderEntity;
import dk.presentation.mapstruct.order.repository.OrderRepository;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class OrderEntityRepositoryTestIT extends ArquillianIntegrationTest {

    @Inject
    OrderRepository orderRepository;

    @Inject
    CustomerRepository customerRepository;

    @Test
    public void createOrderWithCustomerData() {
        //Given
        final CustomerEntity customer = customerRepository.find(2L);
        final CustomerEntity shipmentCustomer = customerRepository.find(3L);

        final OrderEntity expectedOrder = OrderEntity.builder()
            .orderId(1L)
            .billingAddress(customer)
            .shipmentAddress(shipmentCustomer).build();

        //When
        final OrderEntity newOrder = orderRepository.placeOrder(expectedOrder);

        //Then
        final OrderEntity actualOrder = orderRepository.findEager(newOrder.getOrderId());
        assertEquals(expectedOrder.getBillingAddress(), actualOrder.getBillingAddress());
        assertEquals(expectedOrder.getShipmentAddress(), actualOrder.getShipmentAddress());
    }

}