package com.nuevaeps.api_auth.infrastructure.adapter.in.web;

import com.nuevaeps.api_auth.domain.model.Usuario;
import com.nuevaeps.api_auth.domain.port.in.AuthUseCase;
import com.nuevaeps.api_auth.domain.port.in.LoginUseCase;
import com.nuevaeps.api_auth.infrastructure.adapter.in.web.dto.RegisterRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthUseCase authUseCase;
    private final LoginUseCase loginUseCase;

    public AuthController(AuthUseCase authUseCase, LoginUseCase loginUseCase) {
        this.authUseCase = authUseCase;
        this.loginUseCase = loginUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<Usuario> register(@RequestBody RegisterRequest request) {
        Usuario usuarioDomain = request.toDomain();
        Usuario usuario = authUseCase.registrar(usuarioDomain, request.getIdTipoDocumento());
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        String token = loginUseCase.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(token);
    }
}