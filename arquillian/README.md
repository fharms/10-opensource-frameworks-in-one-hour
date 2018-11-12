# Simple Arquillian demo example with Thorntail.

This example takes a normal JAX-RS build, show the simplest configuration for running an JAX-RS application,
and integrate test it with Arquillian. 

## Run

For building and run the integration test

    mvnw install

For running the application

    mvnw thorntail:run

## Use

For place an order POST a body with the payload the path "orders/draft/" 

    curl -X POST \
      http://localhost:8080/rest/orders/draft \
      -H 'Content-Type: application/json' \
      -H 'cache-control: no-cache' \
      -d '{
        "orderId": 2,
        "comment": "Can you ship today",
        "billingAddress": {
            "customerId": 5,
            "firstName": "Rosamund",
            "lastName": "Pike",
            "address": "Hollywood Blvd 3",
            "zipCode": 90038,
            "city": "Hollywood"
        },
        "shipmentAddress": 5,
        "orderItems": [
            {
                "itemId": 1,
                "productId": "DVD-STARWARS-6",
                "title": "Return of the Jedi",
                "qty": 1,
                "price": 100.0
            }
        ]
    }'
    
For retrieving the order again, make a GET to "orders/draft/{id:[1-4]}"   
        
        curl -X GET http://localhost:8080/rest/orders/draft/2