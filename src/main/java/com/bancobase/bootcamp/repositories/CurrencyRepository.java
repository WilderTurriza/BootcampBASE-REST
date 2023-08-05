package com.bancobase.bootcamp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bancobase.bootcamp.schemas.CurrencySchema;

@Repository
public interface CurrencyRepository extends JpaRepository<CurrencySchema, String> {
	CurrencySchema findBySymbol(String symbol);
}
