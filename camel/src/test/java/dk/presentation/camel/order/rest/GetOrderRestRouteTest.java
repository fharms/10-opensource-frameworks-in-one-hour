package dk.presentation.camel.order.rest;

import io.restassured.http.ContentType;
import org.apache.camel.test.spring.UseAdviceWith;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = "camel.springboot.jmxEnabled=false")
@UseAdviceWith
public class GetOrderRestRouteTest {

    @Test
    public void getNoneExistingOrder() {
        //Read the created order
        given()
            .log().ifValidationFails()
            .port(8080)
            .baseUri("http://localhost/rest")
            .contentType(ContentType.JSON)
        .when()
            .get("/orders/draft/{orderId}", 5L)
        .then()
            .assertThat().body(equalTo("Unable to retrieve order with id 5"))
            .statusCode(404);
    }
}