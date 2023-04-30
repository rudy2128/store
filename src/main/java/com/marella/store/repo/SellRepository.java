package com.marella.store.repo;

import com.marella.store.model.Sell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public interface SellRepository extends JpaRepository<Sell,Long> {

    @Query(value = "SELECT COALESCE(SUM(subtotal), 0) FROM sell_table WHERE id > 0", nativeQuery = true)
    double totalSell (@Param("subtotal") Double subtotal);
    // get all, but pagable

    @Query(value = "SELECT * FROM sell_table i WHERE i.date like %:date%",nativeQuery = true)
    List<Sell> findAllToday(@Param("date") String date);

    @Query(value = "SELECT * FROM sell_table WHERE LAST_UPDATED >= :startDate AND LAST_UPDATED <= :endDate", nativeQuery = true)
    List<Sell> getAllBetweenDates(@Param("startDate") String startDate, @Param("endDate") String endDate);


}
