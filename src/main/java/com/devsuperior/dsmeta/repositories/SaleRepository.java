package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
	
	
	@Query("SELECT obj.seller.name, obj.amount " +
		   "FROM Sale obj " +
	       "WHERE obj.date >= :dataMin AND obj.date <= :dataFin " +
	       "AND obj.seller.name LIKE UPPER(CONCAT('%', :name , '%')) ")
	
	Page<Sale> searchReport1(LocalDate dataIni, LocalDate dataFin, String name, Pageable pageable);

}
