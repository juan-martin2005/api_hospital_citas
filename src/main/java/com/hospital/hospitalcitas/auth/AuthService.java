package com.hospital.hospitalcitas.auth;

import com.hospital.hospitalcitas.jwt.JwtService;
import com.hospital.hospitalcitas.models.Estado;
import com.hospital.hospitalcitas.repositories.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final IUsuarioRepository usuarioRepository;
    private final JwtService jwtService;

    public String login(LoginRequest loginRequest) {

        boolean isUsuarioInactivo = usuarioRepository.existsByUsernameAndEstado(loginRequest.getUsername(), Estado.INACTIVO);
        if (isUsuarioInactivo) {
            throw new BadCredentialsException("");
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        String token = jwtService.getToken(userDetails);
        System.out.println("Intentando autenticar usuario: " + loginRequest.getUsername());
        return token;

    }
}
