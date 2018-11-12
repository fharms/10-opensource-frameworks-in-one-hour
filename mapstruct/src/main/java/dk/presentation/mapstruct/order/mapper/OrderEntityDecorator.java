package dk.presentation.mapstruct.order.mapper;

import dk.presentation.mapstruct.order.entity.OrderEntity;
import dk.presentation.mapstruct.order.model.Order;

/**
 * The purpose of the decorator is to make sure if the billing and shipment address is the same address, it also point to the same instance.
 */
public abstract class OrderEntityDecorator implements OrderEntityOrderDTOMapper {

    private final OrderEntityOrderDTOMapper delegate;

    OrderEntityDecorator(OrderEntityOrderDTOMapper delegate) {
        this.delegate = delegate;
    }

    /**
     * Handle the cases where the billing and shipment point is equal and should point to the same instance
     */
    @Override
    public OrderEntity toOrderEntity(Order order) {
        final OrderEntity orderEntity = delegate.toOrderEntity(order);
        if (order.getBillingAddress().equals(order.getShipmentAddress())) {
            orderEntity.setShipmentAddress(orderEntity.getBillingAddress());
        }
        return orderEntity;
    }

    /**
     * Handle the cases where the billing and shipment point is equal and should point to the same instance
     */
    @Override
    public Order toOrderDTO(OrderEntity orderEntity) {
        final Order order = delegate.toOrderDTO(orderEntity);
        if (order.getBillingAddress().equals(order.getShipmentAddress())) {
            order.setShipmentAddress(order.getBillingAddress());
        }
        return order;
    }
}
