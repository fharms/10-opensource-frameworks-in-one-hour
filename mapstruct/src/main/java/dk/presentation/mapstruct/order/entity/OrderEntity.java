package dk.presentation.mapstruct.order.entity;

import dk.presentation.mapstruct.customer.entity.CustomerEntity;
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
