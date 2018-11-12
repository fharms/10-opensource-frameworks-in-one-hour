package dk.presentation.springboot.repositories;

import dk.presentation.springboot.entities.Customer;
import io.swagger.annotations.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "customer", path = "customer")
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {

    @ApiOperation("Find a Customer by its name")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", paramType = "query")
    })
    List<Customer> findByName(@Param("name") @RequestParam("name") String name);

}
