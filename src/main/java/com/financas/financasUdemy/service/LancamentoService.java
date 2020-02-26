package com.financas.financasUdemy.service;

import java.util.List;

import com.financas.financasUdemy.model.entity.Lancamento;
import com.financas.financasUdemy.model.enums.StatusLancamento;

public interface LancamentoService {

	Lancamento salvar(Lancamento lancamento);
	
	Lancamento atualizar(Lancamento lancamento);
	
	void deletar(Lancamento lancamento);
	
	List<Lancamento> buscar(Lancamento lancamentoFiltro);
	
	void atualizarStatus(Lancamento lancamento, StatusLancamento statusLancamento);
	
	void validar(Lancamento lancamento);
}
