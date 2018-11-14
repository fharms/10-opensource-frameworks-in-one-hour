package dk.presentation.mapstruct.customer.mapper;

import dk.presentation.mapstruct.customer.entity.CustomerEntity;
import dk.presentation.mapstruct.customer.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "cdi")
public interface CustomerEntityCustomerDTOMapper {

    @Mappings({
        @Mapping(source = "firstName", target = "givenName"),
        @Mapping(source = "lastName", target = "sureName")
    })
    Customer toCustomerDTO(CustomerEntity customer);

    @Mappings({
        @Mapping(source = "givenName", target = "firstName"),
        @Mapping(source = "sureName", target = "lastName")
    })
    CustomerEntity toCustomerEntity(Customer customer);
}
