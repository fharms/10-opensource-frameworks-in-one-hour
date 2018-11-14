import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class CustomerTest {
    private int id;
    private String firstName;
    private String lastName;

    @Before
    public void initialize() {
        id = 1;
        firstName = "John";
        lastName = "Doe";
    }

    @Test
    public void constructorParametersAccessible() {
        Customer customer = new Customer(id, firstName, lastName);

        assertThat(customer.getId(), equalTo(id));
        assertThat(customer.getFirstName(), equalTo(firstName));
        assertThat(customer.getLastName(), equalTo(lastName));
    }

    @Test
    public void idParameter() {
        Customer customer = new Customer();
        customer.setId(id);

        assertThat(customer.getId(), equalTo(id));
    }

    @Test
    public void firstNameParameter() {
        Customer customer = new Customer();
        customer.setFirstName(firstName);

        assertThat(customer.getFirstName(), equalTo(firstName));
    }

    @Test
    public void lastNameParameter() {
        Customer customer = new Customer();
        customer.setLastName(lastName);

        assertThat(customer.getLastName(), equalTo(lastName));
    }

    @Test
    public void getFullName() {
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);

        assertThat(customer.getFullName(), equalTo("Doe, John"));
    }
}
