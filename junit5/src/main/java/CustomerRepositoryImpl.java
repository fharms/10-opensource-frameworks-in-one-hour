import java.util.*;

public class CustomerRepositoryImpl implements CustomerRepository {
    private Map<Integer, Customer> customers;

    public CustomerRepositoryImpl() {
        this.customers = new HashMap<>();
        this.customers.put(1, new Customer(1, "John", "Doe"));
        this.customers.put(2, new Customer(2, "Jane", "Doe"));
    }

    @Override
    public List<Customer> getCustomers() {
        return Collections.unmodifiableList(new ArrayList<>(customers.values()));
    }

    @Override
    public Customer getCustomer(int id) {
        return this.customers.get(id);
    }
}
