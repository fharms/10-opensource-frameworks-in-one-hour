package dk.presentation.mapstruct.order.mapper;

import dk.presentation.mapstruct.customer.mapper.CustomerEntityCustomerDTOMapper;
import dk.presentation.mapstruct.order.entity.OrderEntity;
import dk.presentation.mapstruct.order.model.Order;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = CustomerEntityCustomerDTOMapper.class)
@DecoratedWith(OrderEntityDecorator.class)
public interface OrderEntityOrderDTOMapper {

    OrderEntityOrderDTOMapper MAP_INSTANCE = Mappers.getMapper( OrderEntityOrderDTOMapper.class );

    Order toOrderDTO(OrderEntity customer);

    OrderEntity toOrderEntity(Order order);
}
