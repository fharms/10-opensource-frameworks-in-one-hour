package dk.presentation.mapstruct.customer.entity;

import dk.presentation.mapstruct.ArquillianIntegrationTest;
import dk.presentation.mapstruct.customer.repository.CustomerRepository;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class CustomerEntityRepositoryTestIT extends ArquillianIntegrationTest {

    @Inject
    CustomerRepository customerRepository;

    @Test
    public void callGetCustomerWithCorrectId() {
        final CustomerEntity customer = customerRepository.find(1L);
        assertEquals("Tom", customer.getFirstName());
        assertEquals("Cruse", customer.getLastName());
        assertEquals("Hollywood Blvd 1", customer.getAddress());
        assertEquals(Long.valueOf(90038), customer.getZipCode());
        assertEquals("Hollywood", customer.getCity());
    }
}