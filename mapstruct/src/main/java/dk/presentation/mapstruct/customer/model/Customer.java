package dk.presentation.mapstruct.customer.model;

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

    String givenName;

    String sureName;

    String address;

    Long zipCode;

    String city;

}