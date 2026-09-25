package com.nuevaeps.api_auth.infrastructure.adapter.in.web.dto;

import com.nuevaeps.api_auth.domain.model.Usuario;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterRequest {

    private String numeroDocumento;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String email;
    private String password;
    private Integer idTipoDocumento;

    public Usuario toDomain() {
        Usuario usuario = new Usuario();
        usuario.setNumeroDocumento(this.numeroDocumento);
        usuario.setPrimerNombre(this.primerNombre);
        usuario.setSegundoNombre(this.segundoNombre);
        usuario.setPrimerApellido(this.primerApellido);
        usuario.setSegundoApellido(this.segundoApellido);
        usuario.setEmail(this.email);
        usuario.setPassword(this.password);
        return usuario;
    }
}
