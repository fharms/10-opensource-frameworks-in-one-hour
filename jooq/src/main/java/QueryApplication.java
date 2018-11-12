import org.jooq.Record;
import org.jooq.Result;

import static dk.presentation.generated.tables.Customer.CUSTOMER;

public class QueryApplication extends JooqApplication {

    public static void main(String[] args) {
        QueryApplication application = new QueryApplication();
        application.with(create -> {
            Result<Record> result = create.select().from(CUSTOMER).fetch();
            for (Record record : result) {
                Long id = record.getValue(CUSTOMER.ID);
                String name = record.getValue(CUSTOMER.NAME);

                System.out.println("ID: " + id + " name: " + name);
            }
        });
    }
}
