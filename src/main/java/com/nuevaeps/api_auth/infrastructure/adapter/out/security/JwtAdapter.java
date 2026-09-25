package com.nuevaeps.api_auth.infrastructure.adapter.out.security;

import com.nuevaeps.api_auth.domain.model.Usuario;
import com.nuevaeps.api_auth.domain.port.out.JwtServicePort;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtAdapter implements JwtServicePort {

    @Value("${seguridad.secret-key}")
    private String secretKey;

    @Value("${seguridad.expiration-time}")
    private Long expirationTime;

    @Override
    public String generarToken(Usuario usuario) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("idUsuario", usuario.getIdUsuario());
        extraClaims.put("numeroDocumento", usuario.getNumeroDocumento());
        extraClaims.put("nombreCompleto", usuario.getPrimerNombre() + " " + usuario.getPrimerApellido());

        return Jwts.builder()
                .claims(extraClaims)
                .subject(usuario.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key)
                .compact();
    }
}
