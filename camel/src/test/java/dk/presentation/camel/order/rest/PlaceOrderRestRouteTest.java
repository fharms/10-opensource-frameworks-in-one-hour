package dk.presentation.camel.order.rest;

import dk.presentation.camel.customer.model.Customer;
import dk.presentation.camel.order.model.Order;
import dk.presentation.camel.order.model.OrderItem;
import io.restassured.http.ContentType;
import org.apache.camel.test.spring.UseAdviceWith;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = "camel.springboot.jmxEnabled=false")
@UseAdviceWith
public class PlaceOrderRestRouteTest {

    @Test
    public void placeOrderRestService() {
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
            .assertThat().body(equalTo(expectedOrderJsonPayload()))
            .statusCode(200);
    }

    private String expectedOrderJsonPayload() {
        return "{\n" +
        "  \"orderId\" : 2,\n" +
        "  \"comment\" : \"Can you ship today\",\n" +
        "  \"status\" : null,\n" +
        "  \"billingAddress\" : {\n" +
        "    \"customerId\" : 5,\n" +
        "    \"firstName\" : \"Rosamund\",\n" +
        "    \"lastName\" : \"Pike\",\n" +
        "    \"address\" : \"Hollywood Blvd 3\",\n" +
        "    \"zipCode\" : 90038,\n" +
        "    \"city\" : \"Hollywood\"\n" +
        "  },\n" +
        "  \"shipmentAddress\" : {\n" +
        "    \"customerId\" : 5,\n" +
        "    \"firstName\" : \"Rosamund\",\n" +
        "    \"lastName\" : \"Pike\",\n" +
        "    \"address\" : \"Hollywood Blvd 3\",\n" +
        "    \"zipCode\" : 90038,\n" +
        "    \"city\" : \"Hollywood\"\n" +
        "  },\n" +
        "  \"orderItems\" : [ {\n" +
        "    \"itemId\" : 1,\n" +
        "    \"productId\" : \"DVD-STARWARS-6\",\n" +
        "    \"title\" : \"Return of the Jedi\",\n" +
        "    \"qty\" : 1,\n" +
        "    \"price\" : 100.0\n" +
        "  } ]\n" +
        "}";
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

        final OrderItem item = OrderItem.builder()
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
