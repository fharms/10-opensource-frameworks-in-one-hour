package dk.presentation.arquillian.order;

import dk.presentation.arquillian.order.model.Order;
import dk.presentation.arquillian.order.repository.OrderRepository;
import lombok.extern.jbosslog.JBossLog;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/orders")
@JBossLog
public class OrderRestService {

    @Inject
    OrderRepository orderRepository;

    @POST
    @Path("/draft")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response placeOrder(Order order) {
        try {
            orderRepository.placeOrder(order);
        } catch (Exception e) {
            log.errorf("Error placing order [%s]", order.getOrderId(), e);
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return Response.ok().build();
    }

    @GET
    @Path("/draft/{orderId:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Order getOrder(@PathParam("orderId") Long orderId) {
        final Order order = orderRepository.findEager(orderId);
        if (order == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return order;
    }


}
