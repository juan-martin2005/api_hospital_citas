package com.hospital.hospitalcitas.jwt;

import com.hospital.hospitalcitas.models.Role;
import com.hospital.hospitalcitas.models.Usuario;
import com.hospital.hospitalcitas.repositories.IUsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final IUsuarioRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            if(jwtService.verificarToken(token) && SecurityContextHolder.getContext().getAuthentication() == null) {
                String username = jwtService.getUsername(token);
                Usuario usuario = userRepository.findByUsername(username).orElseThrow();
                UserDetails user = User
                        .withUsername(usuario.getUsername())
                        .password(usuario.getPassword())
                        .authorities(usuario.getRol().stream().map(Role::getNombre).toArray(String[]::new))
                        .build();
                Authentication auth = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(request, response);
    }
}
