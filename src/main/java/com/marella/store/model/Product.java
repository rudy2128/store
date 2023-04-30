package com.marella.store.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "product_table")
public class Product implements Serializable {
    private static final long serialVersionUid = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String kode_brg;
    private String name;

    @NotNull
    private Double hpp;
    @NotNull
    private Double price;
    @NotNull
    private Integer stock;
    private Double total;
   
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @ManyToMany
    @JoinTable(name = "tbl_product_supplier",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "supplier_id")
    )
    private Set<Supplier> suppliers;

    @ManyToMany(mappedBy = "products")
    private Set<Buy> buys;




}
