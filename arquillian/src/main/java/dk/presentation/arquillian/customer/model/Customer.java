package dk.presentation.arquillian.customer.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Represent an customer entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "customers")
@JsonIdentityInfo(scope=Customer.class, generator= ObjectIdGenerators.PropertyGenerator.class, property="customerId")
public class Customer {

    @Id
    Long customerId;

    @Basic
    String firstName;

    @Basic
    String lastName;

    @Basic
    String address;

    @Basic
    Long zipCode;

    @Basic
    String city;

}