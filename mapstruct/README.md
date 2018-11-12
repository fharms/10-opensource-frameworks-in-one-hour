# Simple MapStruct demo example with Arquillian and Thorntail.

This example takes the demo application from the Arquillian map and extend it with new DTO objects
and MapStruct mappers.

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
      -H 'Postman-Token: b81bcb1a-f49e-44f9-a6ec-f6f887ebf8c1' \
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