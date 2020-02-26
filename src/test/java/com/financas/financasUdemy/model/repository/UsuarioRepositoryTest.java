package com.financas.financasUdemy.model.repository;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.financas.financasUdemy.model.entity.Usuario;



@SpringBootTest
@RunWith(SpringRunner.class)
public class UsuarioRepositoryTest {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Test
	public void deveVerificarAExistenciaDeUmEmail() {

		// cenario
		Usuario usuario = Usuario.builder().nome("usuario").email("Usuario@email.com").build();
		usuarioRepository.save(usuario);
		
		// açao/ execuçao
		boolean result = usuarioRepository.existsByEmail("Usuario@email.com");
		
		// verificacao
		Assertions.assertThat(result).isTrue();
		
	}

}
