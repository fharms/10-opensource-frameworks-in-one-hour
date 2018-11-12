package dk.presentation.springboot.entities;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

import static javax.persistence.GenerationType.IDENTITY;

@ApiModel
@Entity
@Table(name = "invoiceline")
public class InvoiceLine {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    private String description;

    private BigDecimal price;

    private int itemcount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoiceid")
    private Invoice invoice;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getItemcount() {
        return itemcount;
    }

    public void setItemcount(int itemcount) {
        this.itemcount = itemcount;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}
