package com.katusoft.despensa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.katusoft.despensa.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
