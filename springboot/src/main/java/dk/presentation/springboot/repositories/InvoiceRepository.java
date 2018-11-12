package dk.presentation.springboot.repositories;

import dk.presentation.springboot.entities.Invoice;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "invoice", path = "invoice")
public interface InvoiceRepository extends PagingAndSortingRepository<Invoice, Long> {

}
