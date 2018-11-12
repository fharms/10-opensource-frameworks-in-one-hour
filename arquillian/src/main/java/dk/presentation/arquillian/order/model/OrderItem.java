package dk.presentation.arquillian.order.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Represent an order item line.
 *
 * @see Order
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "order_items")
public class OrderItem {

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
