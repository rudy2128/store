package com.marella.store.repo;

import com.marella.store.model.Customer;
import com.marella.store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    @Query(value = "select * from customer_table s where s.name like %:keyword% or s.phone like %:keyword%", nativeQuery = true)
    List<Customer> findByKeyword(@Param("keyword") String keyword);

}
