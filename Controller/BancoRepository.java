package com.example.appwebsenai.controller;

import com.example.appwebsenai.model.ContaCorrentePF;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BancoRepository extends CrudRepository<ContaCorrentePF, Long> {
	@Query("SELECT c FROM ContaCorrentePF c ORDER BY c.numeroConta desc")
	List<ContaCorrentePF> findTopByOrderByIdDesc();
}