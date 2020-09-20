package com.sj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sj.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
