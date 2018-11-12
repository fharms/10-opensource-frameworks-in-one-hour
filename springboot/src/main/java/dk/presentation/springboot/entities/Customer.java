package dk.presentation.springboot.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@ApiModel
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @ApiModelProperty(notes = "The ID of the Customer")
    private long id;

    @ApiModelProperty(notes = "The name of the Customer")
    private String name;

    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @ApiModelProperty(hidden = true)
    private List<Invoice> invoices = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }
}
