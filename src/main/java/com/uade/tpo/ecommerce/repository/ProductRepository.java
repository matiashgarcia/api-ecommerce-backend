package com.uade.tpo.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.ecommerce.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  @Query(value = "select * from product where category_id = ?1", nativeQuery = true)
  List<Product> findByCategoryId(String id);

  @Query(value = "select * from product where title like concat('%',?1,'%')", nativeQuery = true)
  List<Product> findBySearch(String search);

}
