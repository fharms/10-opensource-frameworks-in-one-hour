package dk.presentation.arquillian.customer.repository;


import dk.presentation.arquillian.customer.model.Customer;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class CustomerRepository {

    @PersistenceContext
    EntityManager em;

    public Customer find(long id) {
        return em.find(Customer.class, id);
    }
}
