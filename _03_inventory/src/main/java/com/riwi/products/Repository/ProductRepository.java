package com.riwi.products.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.products.Entity.Product;
import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
    public List<Product> findByName(String name);
}
