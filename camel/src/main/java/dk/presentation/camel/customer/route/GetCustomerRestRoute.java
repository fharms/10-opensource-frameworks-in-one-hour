package dk.presentation.camel.customer.route;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.component.jpa.JpaConstants;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class GetCustomerRestRoute extends SpringRouteBuilder  {

    public static final String GET_CUSTOMER_ROUTE_ID = "getCustomerRoute";

    public void configure() {
        //formatter:off

        onException(Exception.class)
            .useOriginalMessage()
            .handled(true)
            .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(500))
            .setBody(simple("Unable to retrieve customer with id ${header.id}"));

        from("direct:getCustomer")
            .routeId(GET_CUSTOMER_ROUTE_ID)
            .process(this::setJpaParameters)
            .setProperty("customerId", header("id"))
            .to("jpa:dk.presentation.camel.customer.entity.CustomerEntity?namedQuery=selectCustomerById")

            .choice()
                .when().body(List.class, list -> list.size() > 0)
                    .setBody(exchange -> exchange.getIn().getBody(List.class).get(0))
                .otherwise()
                    .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(404))
                    .setBody(simple("Unable to retrieve customer with id ${property.customerId}"))
            .end();

        //formatter:oon
    }

    private void setJpaParameters(Exchange e) {
        e.getIn().setHeader(JpaConstants.JPA_PARAMETERS_HEADER,
            Collections.singletonMap("customerId", e.getIn().getHeader("id", Long.class)));
    }
}
