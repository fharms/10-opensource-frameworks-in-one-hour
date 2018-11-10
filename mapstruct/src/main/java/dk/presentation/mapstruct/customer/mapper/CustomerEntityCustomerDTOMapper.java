package dk.presentation.mapstruct.customer.mapper;

import dk.presentation.mapstruct.customer.entity.CustomerEntity;
import dk.presentation.mapstruct.customer.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "cdi")
public interface CustomerEntityCustomerDTOMapper {

    CustomerEntityCustomerDTOMapper MAPPER = Mappers.getMapper(CustomerEntityCustomerDTOMapper.class);

    Customer toCustomerDTO(CustomerEntity customer);
}
