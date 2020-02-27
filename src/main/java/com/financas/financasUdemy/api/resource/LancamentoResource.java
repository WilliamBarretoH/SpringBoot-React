package com.financas.financasUdemy.api.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financas.financasUdemy.api.dto.LancamentoDTO;
import com.financas.financasUdemy.exception.RegraNegocioException;
import com.financas.financasUdemy.model.entity.Lancamento;
import com.financas.financasUdemy.model.entity.Usuario;
import com.financas.financasUdemy.model.enums.TipoLancamento;
import com.financas.financasUdemy.service.LancamentoService;
import com.financas.financasUdemy.service.UsuarioService;

@RestController
@RequestMapping("/api")
public class LancamentoResource {
	
	private LancamentoService lancamentoService;
	private UsuarioService  usuarioService;

	public LancamentoResource(LancamentoService lancamentoService) {
		this.lancamentoService = lancamentoService;
	}
	
	@PostMapping
	public ResponseEntity salvar(@RequestBody LancamentoDTO lancamentoDTO) {
			
	}
	
	private Lancamento converter(LancamentoDTO lancamentoDTO) {
		Lancamento lancamento = new Lancamento();
		lancamento.setAno(lancamentoDTO.getAno());
		lancamento.setMes(lancamentoDTO.getMes());
		lancamento.setValor(lancamentoDTO.getValor());
		
		Usuario usuario = usuarioService.obterPorID(lancamentoDTO.getUsuario())
			.orElseThrow( () -> new RegraNegocioException("Informe um usuario valido"));
		
		lancamento.setUsuario(usuario);
		lancamento.setTipo(TipoLancamento.valueOf(lancamentoDTO.getTipo()));
		
		return lancamento;
		
		
	}
}
