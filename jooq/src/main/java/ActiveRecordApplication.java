import dk.presentation.generated.tables.records.CustomerRecord;
import org.jooq.Record;
import org.jooq.Result;

import static dk.presentation.generated.tables.Customer.CUSTOMER;

public class ActiveRecordApplication extends JooqApplication {

    public static void main(String[] args) {
        ActiveRecordApplication application = new ActiveRecordApplication();
        application.with(create -> {
            Result<CustomerRecord> customers = create.fetch(CUSTOMER);
            for (CustomerRecord customer : customers) {
                String name = customer.getName();
                customer.setName("Updated by jOOQ: " + name);
                customer.store();
            }
        });
    }
}
