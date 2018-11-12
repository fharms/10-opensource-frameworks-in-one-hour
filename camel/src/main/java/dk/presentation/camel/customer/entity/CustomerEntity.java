package dk.presentation.camel.customer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@NamedQueries({
    @NamedQuery(name = "selectCustomerById", query = "select c from CustomerEntity c where c.customerId = :customerId")
})
public class CustomerEntity {

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