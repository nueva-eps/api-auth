package com.nuevaeps.api_auth.domain.port.out;

import com.nuevaeps.api_auth.domain.model.Usuario;

public interface JwtServicePort {
    String generarToken(Usuario usuario);
}
