package dk.presentation.mapstruct.order;

import dk.presentation.mapstruct.order.entity.OrderEntity;
import dk.presentation.mapstruct.order.model.Order;
import dk.presentation.mapstruct.order.repository.OrderRepository;
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

import static dk.presentation.mapstruct.order.mapper.OrderEntityOrderDTOMapper.MAP_INSTANCE;

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
            orderRepository.placeOrder(MAP_INSTANCE.toOrderEntity(order));
        } catch (Exception e) {
            log.errorf(e,"Error placing order [%s]", order.getOrderId());
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return Response.ok().build();
    }

    @GET
    @Path("/draft/{orderId:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Order getOrder(@PathParam("orderId") Long orderId) {
        final OrderEntity order = orderRepository.findEager(orderId);
        if (order == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return MAP_INSTANCE.toOrderDTO(order);
    }


}
