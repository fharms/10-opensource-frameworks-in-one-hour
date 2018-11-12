package dk.presentation.mapstruct.order;

import dk.presentation.mapstruct.ArquillianIntegrationTest;
import dk.presentation.mapstruct.customer.model.Customer;
import dk.presentation.mapstruct.order.model.Order;
import dk.presentation.mapstruct.order.model.OrderItem;
import io.restassured.http.ContentType;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(Arquillian.class)
public class OrderEntityRestServiceTestIT extends ArquillianIntegrationTest {

    @Test
    public void placeOrderWithRestAPI() {
        final Order expectedOrder = buildOrder();

        //Place the order
        given()
            .log().ifValidationFails()
            .port(8080)
            .baseUri("http://localhost/rest")
            .contentType(ContentType.JSON)
            .body(expectedOrder)
        .when()
            .post("/orders/draft")
        .then()
            .statusCode(200);

        //Read the created order
        given()
            .log().ifValidationFails()
            .port(8080)
            .baseUri("http://localhost/rest")
            .contentType(ContentType.JSON)
        .when()
            .get("/orders/draft/{orderId}", 2L)
        .then()
            .assertThat().body(equalTo("{\"orderId\":2,\"comment\":\"Can you ship today\",\"status\":\"draft\",\"billingAddress\":{\"customerId\":5,\"firstName\":\"Rosamund\",\"lastName\":\"Pike\",\"address\":\"Hollywood Blvd 3\",\"zipCode\":90038,\"city\":\"Hollywood\"},\"shipmentAddress\":5,\"orderItems\":[{\"itemId\":1,\"productId\":\"DVD-STARWARS-6\",\"title\":\"Return of the Jedi\",\"qty\":1,\"price\":100.0}]}"))
            .statusCode(200);
    }

    private Order buildOrder() {
        long orderId = 2;
        final Customer customer = Customer.builder()
            .customerId(5L)
            .firstName("Rosamund")
            .lastName("Pike")
            .address("Hollywood Blvd 3")
            .zipCode(90038L)
            .city("Hollywood").build();

        final OrderItem item = OrderItem.builder().build().builder()
            .itemId(1)
            .productId("DVD-STARWARS-6")
            .title("Return of the Jedi")
            .qty(1)
            .price(100.00)
            .build();

        final Order order = Order.builder()
            .orderId(orderId)
            .comment("Can you ship today")
            .billingAddress(customer)
            .shipmentAddress(customer)
            .orderItems(Arrays.asList(item))
            .build();

        return order;
    }
}