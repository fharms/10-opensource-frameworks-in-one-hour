package dk.presentation.thorntail.customer;

import dk.presentation.thorntail.customer.entity.Customer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/customers")
public class CustomerRestService {

    static private Map<Long, Customer> customers = new HashMap();

    static {
        //setup a data for demo purpose.
        customers.put(1L, Customer.builder().customerId(1).firstName("Tom").lastName("Cruse").address("Hollywood Blvd 1").zipCode(90038L).city("Hollywood").build());
        customers.put(2L, Customer.builder().customerId(2).firstName("Tom").lastName("Hanks").address("Hollywood Blvd 2 ").zipCode(90038L).city("Hollywood").build());
        customers.put(3L, Customer.builder().customerId(3).firstName("Halle").lastName("Berry").address("Hollywood Blvd 3").zipCode(90038L).city("Hollywood").build());
        customers.put(4L, Customer.builder().customerId(4).firstName("Natalie").lastName("Portman").address("Hollywood Blvd 4").zipCode(90038L).city("Hollywood").build());
    }

    /**
     * Get the customer for the specified id, if the customer id not found the response code {@link Response.Status#NOT_FOUND} is returned
     * @param id customer id
     * @return A object for the specified customer
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id:[0-9][0-9]*}")
    public Customer getCustomer(@PathParam("id") long id) {
        Customer customer = customers.get(id);
        if (customer == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return customer;
    }


}
