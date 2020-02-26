package com.financas.financasUdemy.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financas.financasUdemy.model.entity.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{

}
