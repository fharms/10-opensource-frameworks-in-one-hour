# Simple Spring Boot demo examples

This example takes a a look at a couple of Spring Boot features.

A demo RESTful web-service has been implemented, in a domain centered around Customers, Invoices and Invoice lines. The
webservice is documented by Swagger, and follow the HATEOAS principles. 

## Run

For building the code, please use:

    mvnw install
    
For running the application, please use:

    mvn spring-boot:run

## Use

Since this service follows the HATEOAS principles, it's structure is fully navigable from the URL: `http://localhost:8080`.

If you prefer a more graphically pleasing level of documenting, Swagger is available from the URL: `http://localhost:8080/swagger-ui.html`.

## Further information

* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring Boot Initializr](https://start.spring.io/)
* [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
* [Spring Data Rest](https://spring.io/projects/spring-data-rest)