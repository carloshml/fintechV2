package com.teste.productApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teste.productApi.entity.Product;

@Repository
public interface ProducRepository extends JpaRepository<Product, Long> {

	 

}
