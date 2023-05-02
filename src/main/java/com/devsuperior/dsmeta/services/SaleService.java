package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleMinDTO> getReport(String minDate, String maxDate, String name, Pageable pageable) {
		LocalDate dataIni = LocalDate.parse(minDate);
		LocalDate dataFin = LocalDate.parse(maxDate);
		
		if (dataFin.equals(null) ) {
			dataFin = LocalDate.ofInstant(Instant.now(),ZoneId.systemDefault());
		}
		if (dataIni.equals(null) ) {
			dataIni = dataFin.minusYears(1L);
		}
		Page<Sale> result = repository.searchReport1(dataIni,dataFin,name,pageable);
		return result.map(x-> new SaleMinDTO(x));
		
	}
}
