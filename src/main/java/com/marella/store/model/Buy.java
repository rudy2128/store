package com.marella.store.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "purchase_table")
public class Buy implements Serializable {
    private static final long serialVersionUid = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String date;
    @ManyToMany
    @JoinTable(name = "tbl_buy_product",
            joinColumns = @JoinColumn(name = "buy_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @NotNull
    private Set<Product> products;
    @NotNull
    private Integer quantity;
    private Double subtotal;


}
