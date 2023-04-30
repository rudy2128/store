package com.marella.store.repo;

import com.marella.store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(value = "SELECT * FROM product_table  t WHERE t.kode_brg=:kode_brg",nativeQuery = true)
    Product findByCode(@Param("kode_brg") String kode_brg);

    Product findByName(String name);

    //Custom query
    @Query(value = "select * from product_table s where s.name like %:keyword% or s.kode_brg like %:keyword%", nativeQuery = true)
    List<Product> findByKeyword(@Param("keyword") String keyword);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE product_table c SET c.stock =:stock , c.total=:total WHERE c.id=:id",nativeQuery = true)
    void updateStock(@Param("id") long id, @Param("stock") Integer stock,@Param("total")Double total);

    @Query(value = "SELECT COALESCE(SUM(total), 0) FROM product_table WHERE id > 0", nativeQuery = true)
    double totalAsset (@Param("subtotal") Double subtotal);


}
