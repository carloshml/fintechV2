package com.teste.salesConsumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teste.salesConsumer.entities.Reversal;

@Repository
public interface ReversalRepository extends JpaRepository<Reversal, Long> {
 

}
