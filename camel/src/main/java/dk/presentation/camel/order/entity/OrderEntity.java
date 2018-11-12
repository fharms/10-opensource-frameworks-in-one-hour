package dk.presentation.camel.order.entity;

import dk.presentation.camel.customer.entity.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Represent an order entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
@NamedQueries({
    @NamedQuery(name = "selectOrderById", query = "select o from OrderEntity o " +
        " join fetch o.shipmentAddress sa" +
        " join fetch o.billingAddress ba" +
        " left join fetch o.orderItems oi where o.orderId = :orderId")
})
public class OrderEntity {

    @Id
    Long orderId;

    @Basic
    String comment;

    @Basic
    String status;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    CustomerEntity billingAddress;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    CustomerEntity shipmentAddress;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "fk_order")
    List<OrderItemEntity> orderItems;
}
