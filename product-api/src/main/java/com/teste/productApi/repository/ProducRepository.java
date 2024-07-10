package com.teste.productApi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teste.productApi.entity.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Repository
public interface ProducRepository extends JpaRepository<Product, Long> {

	Optional<Product> findByName(@NotNull @NotBlank String name);

	 

}
