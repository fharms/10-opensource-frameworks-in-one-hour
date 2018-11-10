package dk.presentation.arquillian.order.model;

import dk.presentation.arquillian.customer.model.Customer;
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
public class Order {

    @Id
    Long orderId;

    @Basic
    String comment;

    @Basic
    String status;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Customer billingAddress;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Customer shipmentAddress;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "fk_order")
    List<OrderItem> orderItems;
}
