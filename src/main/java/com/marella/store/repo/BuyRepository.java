package com.marella.store.repo;

import com.marella.store.model.Buy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyRepository extends JpaRepository<Buy,Long> {
    @Query(value = "SELECT COALESCE(SUM(subtotal), 0) FROM purchase_table WHERE id > 0", nativeQuery = true)
    double totalBuy (@Param("subtotal") Double subtotal);
}
