package dk.presentation.camel.order.route;

import dk.presentation.camel.order.mapper.OrderEntityOrderDTOMapper;
import dk.presentation.camel.order.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PlaceOrderRestRoute extends SpringRouteBuilder  {

    public static final String POST_ORDER_ROUTE_ID = "PostOrderRoute";

    public void configure() {
        from("direct:placeOrder")
            .onException(Exception.class)
                .log(LoggingLevel.ERROR, "Error placing order ${exception.stacktrace}")
                .useOriginalMessage()
                .handled(true)
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(500))
                .setBody(simple("Unable to place order with id ${header.orderId}"))
            .end()

            .routeId(POST_ORDER_ROUTE_ID)
            .transform()
                .body(Order.class, order -> OrderEntityOrderDTOMapper.MAP_INSTANCE.toOrderEntity(order))
            .to("jpa:dk.presentation.camel.customer.entity.OrderEntity");
    }

}
