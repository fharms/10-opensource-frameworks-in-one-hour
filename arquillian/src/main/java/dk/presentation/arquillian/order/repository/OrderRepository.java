package dk.presentation.arquillian.order.repository;

import dk.presentation.arquillian.order.model.Order;
import lombok.extern.jbosslog.JBossLog;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@ApplicationScoped
@JBossLog
public class OrderRepository {

    @PersistenceContext
    EntityManager em;

    /**
     * Find an order with is's associated order lines for the specified order id
     *
     * @param orderId - Order id to retrieve
     * @return an order with is's associated order lines
     */
    @Transactional
    public Order findEager(Long orderId) {
        log.infof("Find order with id [%d]", orderId);
        final TypedQuery<Order> query = em.createQuery("select o from Order o " +
            "join fetch o.shipmentAddress sa " +
            "join fetch o.billingAddress ba " +
            "left join fetch o.orderItems oi " +
            "where o.orderId = :orderId", Order.class);
        query.setParameter("orderId", orderId);
        return query.getSingleResult();
    }

    /**
     * Place an new draft order
     * @param order
     * @return the created order
     */
    @Transactional
    public Order placeOrder(Order order) {
        order.setStatus("draft");
        return em.merge(order);
    }
}
