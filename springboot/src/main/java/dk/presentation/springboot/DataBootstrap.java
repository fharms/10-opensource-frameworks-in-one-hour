package dk.presentation.springboot;

import dk.presentation.springboot.entities.Customer;
import dk.presentation.springboot.entities.Invoice;
import dk.presentation.springboot.entities.InvoiceLine;
import dk.presentation.springboot.repositories.CustomerRepository;
import dk.presentation.springboot.repositories.InvoiceLineRepository;
import dk.presentation.springboot.repositories.InvoiceRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Service
public class DataBootstrap implements InitializingBean {
    public static final int MAX_NUMBER_OF_INVOICES = 10;
    public static final String[] ARTICLE_NAMES = new String[]{
            "Pencil",
            "Ballpoint pen",
            "Eraser",
            "Tape",
            "Paper clip"
    };

    private final CustomerRepository customerRepository;
    private final InvoiceRepository invoiceRepository;
    private final InvoiceLineRepository invoiceLineRepository;

    public DataBootstrap(CustomerRepository customerRepository,
                         InvoiceRepository invoiceRepository,
                         InvoiceLineRepository invoiceLineRepository) {

        this.customerRepository = customerRepository;
        this.invoiceRepository = invoiceRepository;
        this.invoiceLineRepository = invoiceLineRepository;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        cleanDatabase();

        Iterable<Customer> customers = createCustomers();
        createInvoices(customers);
    }

    private List<Customer> createCustomers() {
        List<Customer> customers = Stream.of("John Doe", "Jane Doe").map(name -> {
            Customer customer = new Customer();
            customer.setName(name);
            return customer;
        }).collect(toList());
        this.customerRepository.saveAll(customers);
        return customers;
    }

    private void createInvoices(Iterable<Customer> customers) {
        customers.forEach(customer -> {
            int numberOfInvoices = randomBetween(1, MAX_NUMBER_OF_INVOICES);
            for (int i = 0; i < numberOfInvoices; i++) {
                LocalDate dueDate = LocalDate.now().plusDays(randomBetween(7, 90));

                Invoice invoice = new Invoice();
                invoice.setDue(dueDate);
                invoice.setCustomer(customer);
                invoiceRepository.save(invoice);

                createInvoiceLines(invoice);
            }
        });
    }

    private void createInvoiceLines(Invoice invoice) {
        List<InvoiceLine> lines = Arrays.stream(ARTICLE_NAMES).map(articleName -> {
            InvoiceLine line = new InvoiceLine();
            line.setInvoice(invoice);
            line.setDescription(articleName);
            line.setItemcount(randomBetween(1, 10));
            line.setPrice(BigDecimal.valueOf((double)randomBetween(1, 20)));
            return line;
        }).collect(toList());
        invoiceLineRepository.saveAll(lines);
    }

    private void cleanDatabase() {
        this.customerRepository.deleteAll();
    }

    private int randomBetween(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }
}
