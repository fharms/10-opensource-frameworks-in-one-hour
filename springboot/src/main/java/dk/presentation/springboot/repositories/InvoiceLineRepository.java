package dk.presentation.springboot.repositories;

import dk.presentation.springboot.entities.InvoiceLine;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "invoiceLine", path = "invoiceLine")
public interface InvoiceLineRepository extends PagingAndSortingRepository<InvoiceLine, Long> {

}
