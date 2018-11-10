package dk.presentation.mapstruct.order.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Represent an order item line on a order.
 *
 * @see OrderEntity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "order_items")
public class OrderItemEntity {

    @Id
    Integer itemId;

    @Basic
    String productId;

    @Basic
    String title;

    @Basic
    int qty;

    @Basic
    double price;
}
