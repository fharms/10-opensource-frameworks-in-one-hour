public class CustomerService {
    private CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public String[] getNamesOfCustomers() {
        return this.repository.getCustomers().stream().map(customer -> customer.getFullName()).toArray(String[]::new);
    }
}
