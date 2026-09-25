package com.nuevaeps.api_auth.domain.port.in;

import com.nuevaeps.api_auth.domain.model.Usuario;

public interface LoginUseCase {
    String login(String email, String password);
}
