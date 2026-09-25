package com.nuevaeps.api_auth.domain.port.out;

import com.nuevaeps.api_auth.domain.model.Usuario;

import java.util.Optional;

public interface UsuarioRepositoryPort {
    Usuario guardar(Usuario usuario, Integer idTipoDocumento);
    Optional<Usuario> buscarPorEmail(String email);
}