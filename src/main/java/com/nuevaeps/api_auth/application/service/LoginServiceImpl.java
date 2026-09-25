package com.nuevaeps.api_auth.application.service;

import com.nuevaeps.api_auth.domain.model.Usuario;
import com.nuevaeps.api_auth.domain.port.in.LoginUseCase;
import com.nuevaeps.api_auth.domain.port.out.JwtServicePort;
import com.nuevaeps.api_auth.domain.port.out.PasswordEncoderPort;
import com.nuevaeps.api_auth.domain.port.out.UsuarioRepositoryPort;

public class LoginServiceImpl implements LoginUseCase {

    private final UsuarioRepositoryPort usuarioRepository;
    private final PasswordEncoderPort passwordEncoder;
    private final JwtServicePort jwtServicePort;

    public LoginServiceImpl(UsuarioRepositoryPort usuarioRepository, PasswordEncoderPort passwordEncoder, JwtServicePort jwtServicePort) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtServicePort = jwtServicePort;
    }

    @Override
    public String login(String email, String passwordPlano) {
        Usuario usuario = usuarioRepository.buscarPorEmail(email)
                .orElseThrow(() -> new RuntimeException("Credenciales incorrectas (Email no encontrado)"));

        boolean esValida = passwordEncoder.matches(passwordPlano, usuario.getPassword());

        if (!esValida) {
            throw new RuntimeException("Credenciales incorrectas (Contraseña errónea)");
        }

        return jwtServicePort.generarToken(usuario);
    }
}
