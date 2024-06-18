package com.teste.fintech.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teste.fintech.entity.Wallet;
import com.teste.fintech.entity.WalletStatement;

@Repository
public interface WalletStatmentRepository extends JpaRepository<WalletStatement, Long> {

	public Optional<List<WalletStatement>> findByWallet(Wallet wallet);

}
