import org.jooq.Record;
import org.jooq.Record3;
import org.jooq.Result;
import org.jooq.impl.DSL;

import static dk.presentation.generated.tables.Customer.CUSTOMER;
import static dk.presentation.generated.tables.Invoice.INVOICE;

public class QueryApplication extends JooqApplication {

    public static void main(String[] args) {
        QueryApplication application = new QueryApplication();
        application.with(create -> {
            Result<Record3<Long, String, Integer>> result = create.select(CUSTOMER.ID, CUSTOMER.NAME, DSL.count(INVOICE).as("count"))
                    .from(INVOICE)
                    .innerJoin(CUSTOMER).on(CUSTOMER.ID.eq(INVOICE.CUSTOMERID))
                    .groupBy(CUSTOMER.ID)
                    .fetch();
            for (Record record : result) {
                Long id = record.getValue(CUSTOMER.ID);
                String name = record.getValue(CUSTOMER.NAME);
                Integer noOfInvoices = (Integer)record.getValue("count");

                System.out.println("ID: " + id + " name: " + name + ", # of invoices: " + noOfInvoices);
            }
        });
    }
}
