package dk.presentation.camel.order.model;

import dk.presentation.camel.order.entity.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represent an order item line on a order.
 *
 * @see OrderEntity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {

    Integer itemId;

    String productId;

    String title;

    int qty;

    double price;
}
