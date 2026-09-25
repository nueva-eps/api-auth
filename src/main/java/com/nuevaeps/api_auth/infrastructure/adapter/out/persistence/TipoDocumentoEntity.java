package com.nuevaeps.api_auth.infrastructure.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tipos_documento", schema = "auth")
public class TipoDocumentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_documento")
    private Integer idTipoDocumento;
    private String codigo;
    private String descripcion;
}