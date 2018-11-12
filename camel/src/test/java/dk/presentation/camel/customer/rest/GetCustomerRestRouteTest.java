package dk.presentation.camel.customer.rest;

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
public class GetCustomerRestRouteTest {

    @Test
    public void getCustomerRestService() {
        //Read the the customer
        given()
            .log().ifValidationFails()
            .port(8080)
            .baseUri("http://localhost/rest")
            .contentType(ContentType.JSON)
        .when()
            .get("/customers/{customerId}", 1L)
        .then()
            .assertThat().body(equalTo("{\n" +
            "  \"customerId\" : 1,\n" +
            "  \"firstName\" : \"Tom\",\n" +
            "  \"lastName\" : \"Cruse\",\n" +
            "  \"address\" : \"Hollywood Blvd 1\",\n" +
            "  \"zipCode\" : 90038,\n" +
            "  \"city\" : \"Hollywood\"\n" +
            "}"))
            .statusCode(200);
    }

    @Test
    public void getNoneExistingCustomerRestService() {
        //Read the the customer
        given()
            .log().ifValidationFails()
            .port(8080)
            .baseUri("http://localhost/rest")
            .contentType(ContentType.JSON)
        .when()
            .get("/customers/{customerId}", 7L)
        .then()
            .assertThat().body(equalTo("Unable to retrieve customer with id 7"))
            .statusCode(404);
    }

}