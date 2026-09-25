package com.nuevaeps.api_auth.domain.port.in;

import com.nuevaeps.api_auth.domain.model.Usuario;

public interface AuthUseCase {
    Usuario registrar(Usuario usuario, Integer idTipoDocumento);
}
