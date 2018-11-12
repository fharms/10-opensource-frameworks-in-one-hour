package dk.presentation.springboot.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@ApiModel
@Entity
@Table(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @ApiModelProperty(notes = "The ID of the Invoice")
    private long id;

    @ApiModelProperty(notes = "The date that the Invoice has to be payed by")
    private LocalDate due;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerid")
    private Customer customer;

    @OneToMany(
            mappedBy = "invoice",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @ApiModelProperty(hidden = true)
    private List<InvoiceLine> lines = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDue() {
        return due;
    }

    public void setDue(LocalDate due) {
        this.due = due;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<InvoiceLine> getLines() {
        return lines;
    }

    public void setLines(List<InvoiceLine> lines) {
        this.lines = lines;
    }
}
