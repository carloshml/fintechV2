package com.teste.salesConsumer.repository;

 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teste.salesConsumer.entities.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

}
