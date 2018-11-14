import java.util.List;

public interface CustomerRepository {
    List<Customer> getCustomers();
    Customer getCustomer(int id);
}
