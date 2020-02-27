package com.financas.financasUdemy.service;

import java.util.Optional;

import com.financas.financasUdemy.model.entity.Usuario;

public interface UsuarioService {
	
	Usuario autenticar(String email, String senha);
	
	Usuario salvaUsuario(Usuario usuario);
	
	void validarEmail(String email);
	
	Optional<Usuario> obterPorID(Long ID);

}
