package com.financas.financasUdemy.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.financas.financasUdemy.exception.RegraNegocioException;
import com.financas.financasUdemy.model.entity.Usuario;
import com.financas.financasUdemy.model.repository.UsuarioRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UsuarioServiceTest {

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Test(expected = Test.None.class)
	public void deveValidarEmail() {
		//cenario
		usuarioRepository.deleteAll();
		
		//acao
		usuarioService.validarEmail("email@email.com");
	}
	
	@Test(expected = RegraNegocioException.class)
	public void deveLancarErroAoValidarEmailQuandoExistirEmailCadastrado() {
		
		//cenario
		Usuario usuario = Usuario.builder().nome("usuario").email("usuario@usuario.com").build();
		usuarioRepository.save(usuario);
		
		//acao
		usuarioService.validarEmail("usuario@usuario.com");
	}

}
