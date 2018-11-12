package dk.presentation.camel.customer.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represent an customer DTO.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIdentityInfo(scope= Customer.class, generator= ObjectIdGenerators.PropertyGenerator.class, property="customerId")
public class Customer {

    Long customerId;

    String firstName;

    String lastName;

    String address;

    Long zipCode;

    String city;

}