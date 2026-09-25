package com.nuevaeps.api_auth.infrastructure.config;

import com.nuevaeps.api_auth.application.service.AuthServiceImpl;
import com.nuevaeps.api_auth.application.service.LoginServiceImpl;
import com.nuevaeps.api_auth.domain.port.in.AuthUseCase;
import com.nuevaeps.api_auth.domain.port.in.LoginUseCase;
import com.nuevaeps.api_auth.domain.port.out.JwtServicePort;
import com.nuevaeps.api_auth.domain.port.out.PasswordEncoderPort;
import com.nuevaeps.api_auth.domain.port.out.UsuarioRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public AuthUseCase authUseCase(
            UsuarioRepositoryPort usuarioRepositoryPort,
            PasswordEncoderPort passwordEncoderPort
    ) {
        return new AuthServiceImpl(usuarioRepositoryPort, passwordEncoderPort);
    }

    @Bean
    public LoginUseCase loginUseCase(
            UsuarioRepositoryPort usuarioRepositoryPort,
            PasswordEncoderPort passwordEncoderPort,
            JwtServicePort jwtServicePort
    ) {
        return new LoginServiceImpl(usuarioRepositoryPort, passwordEncoderPort, jwtServicePort);
    }
}
