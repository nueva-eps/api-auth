package com.nuevaeps.api_auth.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoDocumento {
    private Integer idTipoDocumento;
    private String codigo;
    private String descripcion;
}
