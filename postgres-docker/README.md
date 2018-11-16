# PostgreSQL server ()Docker container)

This is not an example, but a supporting feature for the examples:

* [Guava](../guava/README.md)
* [Spring Boot](../springboot/README.md)

## Run

For utilizing the server / utility, you can execute the following (shell) script:

    postgres.sh

It takes a couple of parameters:

* `build` (builds the PostgreSQL image)
  
  **Notice:** the database is initialized with the schema from the [Spring Boot](../springboot/README.md)-example.
  
* `start` (starts the image)
* `client` (starts a CLI client, for interacting with the PostgreSQL container)
* `stop` (stops the running PostgreSQL container)
* `clear` (remove any of the PostgreSQL containers)
* `clear-all` (removes all stopped docker containers, regardless of what they are)


## Further information

* [Docker (website)](https://www.docker.com/)
* [PostgreSQL Docker image](https://hub.docker.com/_/postgres/)
