package com.hospital.hospitalcitas.jwt;

import com.hospital.hospitalcitas.models.Usuario;
import com.hospital.hospitalcitas.repositories.IUsuarioRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtService {

    @Value("${jwt.secureKey}")
    private String secretString;

    private final IUsuarioRepository userRepository;

    public String getToken(UserDetails usuario){
        return Jwts.builder()
                .subject(usuario.getUsername())
                .signWith(getSecretKey())
                .claims(getClaims(usuario))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + (60*10000*30)))
                .compact();
    }

    private Map<String, Object> getClaims(UserDetails usuario) {
        Usuario usuarioDb = userRepository.findByUsername(usuario.getUsername())
                .orElseThrow();

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", usuario.getAuthorities());
        claims.put("nombre", usuarioDb.getNombre());
        claims.put("apellido", usuarioDb.getApellido());
        return claims;
    }

    public SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretString));
    }

    public boolean verificarToken(String token){
        try {
            Jwts.parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        }catch (JwtException ex){
            throw new JwtException("Token invalido: " + ex);
        }
    }

    public String getUsername(String token) {
        return Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token).getPayload().getSubject();
    }
}
