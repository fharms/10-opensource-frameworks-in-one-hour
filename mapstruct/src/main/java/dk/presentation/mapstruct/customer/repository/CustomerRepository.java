package dk.presentation.mapstruct.customer.repository;


import dk.presentation.mapstruct.customer.entity.CustomerEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class CustomerRepository {

    @PersistenceContext
    EntityManager em;

    public CustomerEntity find(long id) {
        return em.find(CustomerEntity.class, id);
    }
}
