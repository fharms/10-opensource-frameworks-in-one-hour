package dk.presentation.camel.order.mapper;

import dk.presentation.camel.order.entity.OrderEntity;
import dk.presentation.camel.order.model.Order;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
@DecoratedWith(OrderEntityDecorator.class)
public interface OrderEntityOrderDTOMapper {

    OrderEntityOrderDTOMapper MAP_INSTANCE = Mappers.getMapper( OrderEntityOrderDTOMapper.class );

    Order toOrderDTO(OrderEntity customer);

    OrderEntity toOrderEntity(Order order);
}
