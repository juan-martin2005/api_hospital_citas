package com.hospital.hospitalcitas.security;

import com.hospital.hospitalcitas.jwt.JwtAuthenticationFilter;
import com.hospital.hospitalcitas.models.Role;
import com.hospital.hospitalcitas.repositories.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter  jwtAuthenticationFilter;
    private final IUsuarioRepository userRepository;

    @Bean
    public PasswordEncoder encode() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return  http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        //*****RUTAS PERMMITIDO PARA TODOS*****//
                        .requestMatchers(HttpMethod.POST,"/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/paciente").permitAll()
                        //*****RUTAS PERMITIDAS PARA EL PACIENTE*****//
                        .requestMatchers(HttpMethod.GET,"/api/doctor/**").hasAuthority("ROLE_PACIENTE")
                        .requestMatchers("/api/cita-medica/**").hasAuthority("ROLE_PACIENTE")
                        //*****RUTAS PERMITIDAS PARA EL DOCTOR*****//
                        .requestMatchers("/api/horario/**").hasAuthority("ROLE_DOCTOR")
                        //*****RUTAS PERMITIDAS PARA EL ADMINISTRADOR*****//
                        .requestMatchers("/api/usuario/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/api/especialidad/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/api/doctor/**").hasAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterAfter(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(sm-> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    public AuthenticationManager authentication(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username).map(

                user -> User.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .authorities(user.getRol().stream()
                                .map(Role::getNombre)
                                .toArray(String[]::new)
                        )
                        .build()

        ).orElseThrow(() -> new UsernameNotFoundException(""));
    }
}
