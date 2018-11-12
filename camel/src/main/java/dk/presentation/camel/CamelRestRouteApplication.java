package dk.presentation.camel;

import dk.presentation.camel.customer.model.Customer;
import dk.presentation.camel.order.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main REST DSL route for customer and order handling.
 */
@Slf4j
@SpringBootApplication
public class CamelRestRouteApplication extends SpringRouteBuilder  {

    /**
     * A main method to start this application.
     */
    public static void main(String[] args) {
        SpringApplication.run(CamelRestRouteApplication.class, args);
    }

    public void configure() throws Exception {
        restConfiguration()
            .contextPath("rest")
            .component("servlet")
            .bindingMode(RestBindingMode.json)
            .dataFormatProperty("prettyPrint", "true");

        rest("customers")
            .get("{id}").outType(Customer.class)
                .to("direct:getCustomer");

        rest("orders")
            .get("draft/{orderId}").outType(Order.class)
                .to("direct:getOrder");

        rest("orders")
            .post("draft")
            .type(Order.class)
                .to("direct:placeOrder");
    }

}
