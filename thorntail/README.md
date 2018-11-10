# Simple Thorntail JAX-RS demo example.

This example takes a normal JAX-RS build, show the simplest configuration for running an JAX-RS application
with Thorntail 

This dependency provides the JAX-RS APIs to your application, so the
project does *not* need to specify those.

## Run

* mvnw thorntail:run
* From your IDE, run class `org.wildfly.swarm.Swarm`

## Use

For testing the application call the path "customer/{id:[1-4]}" 

    curl -X GET http://localhost:8080/rest/customer/1
