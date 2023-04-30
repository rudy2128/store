package com.marella.store.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "supplier_table")
public class Supplier implements Serializable {
    private static final long serialVersionUid = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String code;
    private String email;
    private String phone;
    private String address;
    @ManyToMany(mappedBy = "suppliers")
    private Set<Product> products;
}
