package dk.presentation.thorntail.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represent an external customer API model.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    long customerId;

    String firstName;

    String lastName;

    String address;

    Long zipCode;

    String city;
}
