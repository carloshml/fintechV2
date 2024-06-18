package com.teste.productApi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teste.productApi.entity.Wallet;

 

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> { 

}
