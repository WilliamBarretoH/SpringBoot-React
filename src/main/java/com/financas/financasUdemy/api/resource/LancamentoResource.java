package com.financas.financasUdemy.api.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financas.financasUdemy.api.dto.LancamentoDTO;
import com.financas.financasUdemy.exception.RegraNegocioException;
import com.financas.financasUdemy.model.entity.Lancamento;
import com.financas.financasUdemy.model.entity.Usuario;
import com.financas.financasUdemy.model.enums.StatusLancamento;
import com.financas.financasUdemy.model.enums.TipoLancamento;
import com.financas.financasUdemy.service.LancamentoService;
import com.financas.financasUdemy.service.UsuarioService;

@RestController
@RequestMapping("/api")
public class LancamentoResource {

	private LancamentoService lancamentoService;
	private UsuarioService usuarioService;

	public LancamentoResource(LancamentoService lancamentoService) {
		this.lancamentoService = lancamentoService;
	}

	@PostMapping
	public ResponseEntity salvar(@RequestBody LancamentoDTO lancamentoDTO) {

		try {
			Lancamento entidade = converter(lancamentoDTO);
			entidade = lancamentoService.salvar(entidade);
			return new ResponseEntity(entidade, HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());

		}
	}
	
	@PutMapping("{id}")
	public ResponseEntity atualizar(@PathVariable("id") Long ID, @RequestBody LancamentoDTO lancamentoDTO) {
		return lancamentoService.obterPorID(ID).map( entity -> {
			try {
				Lancamento lancamento = converter(lancamentoDTO);
				lancamento.setId(entity.getId());
				lancamentoService.atualizar(lancamento);
				return ResponseEntity.ok(lancamento);
			}catch (Exception e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}	
		}).orElseGet( () -> 
			new ResponseEntity("Lancamento nao encontrado na base de dados", HttpStatus.BAD_REQUEST));
	}

	private Lancamento converter(LancamentoDTO lancamentoDTO) {
		Lancamento lancamento = new Lancamento();
		lancamento.setId(lancamentoDTO.getId());
		lancamento.setAno(lancamentoDTO.getAno());
		lancamento.setMes(lancamentoDTO.getMes());
		lancamento.setValor(lancamentoDTO.getValor());

		Usuario usuario = usuarioService.obterPorID(lancamentoDTO.getUsuario())
				.orElseThrow(() -> new RegraNegocioException("Informe um usuario valido"));

		lancamento.setUsuario(usuario);
		lancamento.setTipo(TipoLancamento.valueOf(lancamentoDTO.getTipo()));
		lancamento.setStatus(StatusLancamento.valueOf(lancamentoDTO.getStatus()));

		return lancamento;

	}
}
