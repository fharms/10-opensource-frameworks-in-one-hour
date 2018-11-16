# Simple JUnit 5 (Jupiter) demo examples

This example takes a look at how-to use JUnit 5 for testing applications (and some of it's features).

The two included samples are:

* `CustomerServiceTest`
   
   Testing using new `@ExtendWith` and `@DisplayName`-annotations, furthermore utilizing a custom `ParameterResolver`
   for performing parameterized testing with a HTTP-populated `Customer`-resource.
    
* `CustomerTest`

   Testing using the legacy JUnit 4 test constructs, and the new `junit-vintage-engine`.

## Run

For building the code, please use:

    mvnw install

For running the application, open the project in your IDE, and run the tests:

* `CustomerServiceTest`
* `CustomerTest`

or execute the maven target by typing:

    mvnw test
    
## Further information

* [JUnit 5](https://junit.org/junit5/)