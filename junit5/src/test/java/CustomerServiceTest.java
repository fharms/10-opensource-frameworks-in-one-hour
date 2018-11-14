import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasItemInArray;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("CustomerService")
@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    private CustomerService service;
    private CustomerRepository repository;

    @BeforeEach
    public void createService(@Mock CustomerRepository repository) {
        service = new CustomerService(repository);
        this.repository = repository;
    }

    @Test
    @DisplayName("should provide a means of retrieving the full names of all Customers")
    public void getNamesOfCustomers() {
        when(repository.getCustomers()).thenReturn(Arrays.asList(
                new Customer(1, "Bob", "Dylan"),
                new Customer(2, "Bruce", "Springsteen")
        ));

        String[] names = service.getNamesOfCustomers();

        verify(repository).getCustomers();
        assertAll("Names",
                () -> assertThat(names, hasItemInArray("Dylan, Bob")),
                () -> assertThat(names, hasItemInArray("Springsteen, Bruce"))
        );
    }

    @Test
    @DisplayName("should test something with a randomly created Customer")
    @ExtendWith(CustomerParameterResolver.class)
    public void parameterized(Customer customer) {
        assertAll("Generated Customer",
                () -> assertThat(customer.getId(), greaterThanOrEqualTo(0)),
                () -> assertThat(customer.getFirstName(), notNullValue()),
                () -> assertThat(customer.getLastName(), notNullValue())
        );
    }

}
