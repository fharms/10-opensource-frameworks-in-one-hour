package dk.presentation.mapstruct.customer;

import dk.presentation.mapstruct.ArquillianIntegrationTest;
import io.restassured.http.ContentType;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(Arquillian.class)
public class CustomerRestServiceTestIT extends ArquillianIntegrationTest {

    @Test
    public void getCustomerWithRestAPI() {
        //Read an existing customer order
        given()
            .log().ifValidationFails()
            .port(8080)
            .baseUri("http://localhost/rest")
            .contentType(ContentType.JSON)
        .when()
            .get("/customers/{id}", 4L)
        .then()
            .assertThat().body(equalTo("{\"customerId\":4,\"firstName\":\"Natalie\",\"lastName\":\"Portman\",\"address\":\"Hollywood Blvd 4\",\"zipCode\":90038,\"city\":\"Hollywood\"}"))
            .statusCode(200);
    }
}