package com.uade.tpo.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.ecommerce.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  @Query(value = "select * from product where user_id = ?1", countQuery = "select count(*) from product where user_id = ?1", nativeQuery = true)
  Page<Product> findAllWithPageable(Long userId, PageRequest pageable);

  @Query(value = "select * from product where category_id = ?1", nativeQuery = true)
  List<Product> findByCategoryId(String categoryId);

  @Query(value = "select * from product where title like concat('%',?1,'%')", nativeQuery = true)
  List<Product> findBySearch(String search);

  @Query(value = "select * from product where id in ?1", nativeQuery = true)
  List<Product> findCartProductsByIds(String[] productIds);

}
