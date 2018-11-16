package dk.presentation.arquillian.customer.repository;

import dk.presentation.arquillian.ArquillianIntegrationTest;
import dk.presentation.arquillian.customer.model.Customer;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class CustomerRepositoryTestIT extends ArquillianIntegrationTest {

    @Inject
    CustomerRepository customerRepository;

    //*** Run the test class instead ***
    @Test
    public void callGetCustomerWithCorrectId() {
        final Customer customer = customerRepository.find(1L);
        assertEquals("Tom", customer.getFirstName());
        assertEquals("Cruse", customer.getLastName());
        assertEquals("Hollywood Blvd 1", customer.getAddress());
        assertEquals(Long.valueOf(90038), customer.getZipCode());
        assertEquals("Hollywood", customer.getCity());
    }
}