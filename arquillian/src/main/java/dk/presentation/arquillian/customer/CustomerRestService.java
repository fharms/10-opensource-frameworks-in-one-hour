package dk.presentation.arquillian.customer;

import dk.presentation.arquillian.customer.repository.CustomerRepository;
import dk.presentation.arquillian.customer.model.Customer;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/customers")
public class CustomerRestService {

    @Inject
    CustomerRepository customerRepository;

    /**
     * Get a customer from the specified id, if the customer id is not found, the response code {@link Response.Status#NOT_FOUND} is returned
     *
     * @param id customer id
     * @return A object for the specified customer
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id:[0-9][0-9]*}")
    public Customer getCustomer(@PathParam("id") long id) {
        Customer customer = customerRepository.find(id);
        if (customer == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return customer;
    }
}
