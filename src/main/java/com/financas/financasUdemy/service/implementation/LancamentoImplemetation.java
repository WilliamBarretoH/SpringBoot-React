package com.financas.financasUdemy.service.implementation;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import com.financas.financasUdemy.exception.RegraNegocioException;
import com.financas.financasUdemy.model.entity.Lancamento;
import com.financas.financasUdemy.model.enums.StatusLancamento;
import com.financas.financasUdemy.model.repository.LancamentoRepository;
import com.financas.financasUdemy.service.LancamentoService;

@Service
public class LancamentoImplemetation implements LancamentoService {

	private LancamentoRepository lancamentoRepository;

	public LancamentoImplemetation(LancamentoRepository lancamentoRepository) {

		this.lancamentoRepository = lancamentoRepository;
	}

	@Override
	@Transactional
	public Lancamento salvar(Lancamento lancamento) {

		return lancamentoRepository.save(lancamento);
	}

	@Override
	@Transactional
	public Lancamento atualizar(Lancamento lancamento) {
		Objects.requireNonNull(lancamento.getId());
		return lancamentoRepository.save(lancamento);
	}

	@Override
	@Transactional
	public void deletar(Lancamento lancamento) {
		Objects.requireNonNull(lancamento.getId());
		lancamentoRepository.delete(lancamento);
	}

	@Override
	@Transactional
	public List<Lancamento> buscar(Lancamento lancamentoFiltro) {
		Example ex = Example.of(lancamentoFiltro,
				ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));

		return lancamentoRepository.findAll(ex);
	}

	@Override
	public void atualizarStatus(Lancamento lancamento, StatusLancamento statusLancamento) {
		lancamento.setStatus(statusLancamento);
		atualizar(lancamento);

	}

	@Override
	public void validar(Lancamento lancamento) {

		if (lancamento.getAno() == null || lancamento.getAno().toString().length() != 4) {
			throw new RegraNegocioException("Informe um ano valido");
		}
		if (lancamento.getMes() == null || lancamento.getMes() < 1 || lancamento.getMes() > 12) {
			throw new RegraNegocioException("informe um mes valido");
		}
		if (lancamento.getUsuario() == null || lancamento.getUsuario().getId() == null) {
			throw new RegraNegocioException("informe um usuario valido");
		}
		if(lancamento.getValor() == null || lancamento.getValor().compareTo(BigDecimal.ZERO) < 1) {
			throw new RegraNegocioException("informe um valor valido");

		}

	}
}