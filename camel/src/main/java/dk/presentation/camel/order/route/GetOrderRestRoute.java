package dk.presentation.camel.order.route;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.component.jpa.JpaConstants;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class GetOrderRestRoute extends SpringRouteBuilder  {

    public static final String GET_ORDER_ROUTE_ID = "GetOrderRoute";

    public void configure() {
        from("direct:getOrder")
            .onException(Exception.class)
                .log(LoggingLevel.ERROR, "Error placing order ${exception.stacktrace}")
                .useOriginalMessage()
                .handled(true)
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(500))
                .setBody(simple("Unable to pace order with id ${header.orderId}"))
            .end()

            .routeId(GET_ORDER_ROUTE_ID)
            .process(this::setJpaParameters)
            .setProperty("orderId", header("orderId"))
            .to("jpa:dk.presentation.camel.order.entity.OrderEntity?namedQuery=selectOrderById")
            .choice()
                .when().body(List.class, list -> list.size() > 0)
                    .setBody(exchange -> exchange.getIn().getBody(List.class).get(0))
            .otherwise()
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(404))
                .setBody(simple("Unable to retrieve order with id ${property.orderId}"))
            .end();
    }

    private void setJpaParameters(Exchange e) {
        e.getIn().setHeader(JpaConstants.JPA_PARAMETERS_HEADER, Collections.singletonMap("orderId", e.getIn().getHeader("orderId", Long.class)));
    }

}
