package com.financas.financasUdemy.service.implementation;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.financas.financasUdemy.exception.ErroAutenticacao;
import com.financas.financasUdemy.exception.RegraNegocioException;
import com.financas.financasUdemy.model.entity.Usuario;
import com.financas.financasUdemy.model.repository.UsuarioRepository;
import com.financas.financasUdemy.service.UsuarioService;

@Service
public class UsuarioImplementation implements UsuarioService {

	private UsuarioRepository usuarioRepository;

	public UsuarioImplementation(UsuarioRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public Usuario autenticar(String email, String senha) {
		Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

		if (!usuario.isPresent()) {
			throw new ErroAutenticacao("Email de usuario nao encontrado");
		}

		if (!usuario.get().getSenha().equals(senha)) {
			throw new ErroAutenticacao("Senha invalida");
		}

		return usuario.get();
	}

	@Override
	@Transactional
	public Usuario salvaUsuario(Usuario usuario) {
		validarEmail(usuario.getEmail());
		return usuarioRepository.save(usuario);
	}

	@Override
	public void validarEmail(String email) {
		boolean existe = usuarioRepository.existsByEmail(email);
		if (existe) {
			throw new RegraNegocioException("Já existe um usuario com esse email");
		}

	}

	@Override
	public Optional<Usuario> obterPorID(Long ID) {
		
		return usuarioRepository.findById(ID);
	}

}
