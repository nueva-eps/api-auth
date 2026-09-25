package com.nuevaeps.api_auth.application.service;

import com.nuevaeps.api_auth.domain.model.Usuario;
import com.nuevaeps.api_auth.domain.port.in.AuthUseCase;
import com.nuevaeps.api_auth.domain.port.out.PasswordEncoderPort;
import com.nuevaeps.api_auth.domain.port.out.UsuarioRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthUseCase {

    private final UsuarioRepositoryPort usuarioRepository;
    private final PasswordEncoderPort passwordEncoder;

    public AuthServiceImpl(UsuarioRepositoryPort usuarioRepository, PasswordEncoderPort passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Usuario registrar(Usuario usuario, Integer idTipoDocumento) {
        String passwordEncriptado = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(passwordEncriptado);

        return usuarioRepository.guardar(usuario, idTipoDocumento);
    }
}
