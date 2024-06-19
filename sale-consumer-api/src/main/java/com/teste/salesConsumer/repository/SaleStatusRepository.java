package com.teste.salesConsumer.repository;

 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.teste.salesConsumer.entities.SaleStatus;

@Repository
public interface SaleStatusRepository extends JpaRepository<SaleStatus, Long> {

}
