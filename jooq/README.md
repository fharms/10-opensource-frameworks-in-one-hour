# Simple jOOQ demo examples

This example takes a a look at a couple of jOOQ features, more specifically:

* Active `Record`s (`org.jooq.Result` and `org.jooq.Record`)
* Queries in jOOQ

## Run

For building the code, please use:

    mvnw install

For running the application, open the project in your IDE, and then run the Java applications:

* `ActiveRecordApplication`
* `QueryApplication`

## Use

This example depends upon a couple of things.

1. The PostgreSQL database, made available from the PostgreSQL dastabase image utility (see: [Postgres Docker example](../postgres-docker/README.md`)).
2. Data in said database, populated by the Spring Boot example (see: [Spring Boot example](../springboot/README.md`)).

These dependencies must be run, before being able to run *this* sample / demo application.

Then run the applications from your IDE.