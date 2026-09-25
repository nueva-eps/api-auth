package com.nuevaeps.api_auth.infrastructure.adapter.out.persistence;

import com.nuevaeps.api_auth.domain.model.TipoDocumento;
import com.nuevaeps.api_auth.domain.model.Usuario;
import com.nuevaeps.api_auth.domain.port.out.UsuarioRepositoryPort;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class UsuarioPersistenceAdapter implements UsuarioRepositoryPort {

    private final UsuarioRepository repository;
    private final EntityManager entityManager;

    public UsuarioPersistenceAdapter(UsuarioRepository repository, EntityManager entityManager) {
        this.repository = repository;
        this.entityManager = entityManager;
    }

    @Override
    public Usuario guardar(Usuario usuario, Integer idTipoDocumento) {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setNumeroDocumento(usuario.getNumeroDocumento());
        entity.setPrimerNombre(usuario.getPrimerNombre());
        entity.setSegundoNombre(usuario.getSegundoNombre());
        entity.setPrimerApellido(usuario.getPrimerApellido());
        entity.setSegundoApellido(usuario.getSegundoApellido());
        entity.setEmail(usuario.getEmail());
        entity.setPassword(usuario.getPassword());

        TipoDocumentoEntity tipoEntity = entityManager.getReference(TipoDocumentoEntity.class, idTipoDocumento);
        entity.setTipoDocumento(tipoEntity);

        UsuarioEntity guardado = repository.save(entity);
        return mapToDomain(guardado);
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return repository.findByEmail(email).map(this::mapToDomain);
    }

    private Usuario mapToDomain(UsuarioEntity entity) {
        TipoDocumento td = new TipoDocumento(
                entity.getTipoDocumento().getIdTipoDocumento(),
                entity.getTipoDocumento().getCodigo(),
                entity.getTipoDocumento().getDescripcion()
        );
        return new Usuario(
                entity.getIdUsuario(),
                td,
                entity.getNumeroDocumento(),
                entity.getPrimerNombre(),
                entity.getSegundoNombre(),
                entity.getPrimerApellido(),
                entity.getSegundoApellido(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getFechaCreacion()
        );
    }
}
