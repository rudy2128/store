package com.marella.store.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "sell_table")
public class Sell implements Serializable {
    private static final long serialVersionUid = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @DateTimeFormat(pattern = "dd/mm/yyyy")
    private Date date;
    @ManyToMany
    @JoinTable(name = "tbl_sell_product",
            joinColumns = @JoinColumn(name = "sell_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    List<Product>products;


    @ManyToMany
    @JoinTable(name = "tbl_sell_customer",
            joinColumns = @JoinColumn(name = "sell_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    List<Customer>customers;
    @NotNull
    private Integer quantity;
    private Double subtotal;


    public void setDate(String format) {
    }
}
