package dk.presentation.mapstruct.order.model;

import dk.presentation.mapstruct.customer.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Represent an order DTO.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    Long orderId;

    String comment;

    String status;

    Customer billingAddress;

    Customer shipmentAddress;

    List<OrderItem> orderItems;
}

