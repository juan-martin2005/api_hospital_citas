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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

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
                        .requestMatchers(HttpMethod.POST,"/api/mercado-pago/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/paciente").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/especialidad").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/doctor/todos").permitAll()
                        //*****RUTAS PERMITIDAS PARA EL PACIENTE*****//
                        .requestMatchers(HttpMethod.GET,"/api/doctor").hasAuthority("ROLE_PACIENTE")
                        .requestMatchers(HttpMethod.GET,"/api/paciente/perfil").hasAuthority("ROLE_PACIENTE")
                        .requestMatchers(HttpMethod.PUT,"/api/paciente/update-perfil").hasAuthority("ROLE_PACIENTE")
                        .requestMatchers(HttpMethod.PATCH,"/api/paciente/update-password").hasAuthority("ROLE_PACIENTE")
                        .requestMatchers(HttpMethod.GET,"/api/cita-medica/mis-citas").hasAuthority("ROLE_PACIENTE")
                        .requestMatchers(HttpMethod.POST,"/api/cita-medica").hasAuthority("ROLE_PACIENTE")
                        .requestMatchers(HttpMethod.PATCH,"/api/cita-medica/cancelar/{id}").hasAuthority("ROLE_PACIENTE")
                        //*****RUTAS PERMITIDAS PARA EL DOCTOR*****//
                        .requestMatchers(HttpMethod.GET,"/api/doctor/perfil").hasAuthority("ROLE_DOCTOR")
                        .requestMatchers(HttpMethod.GET,"/api/cita-medica/pacientes").hasAuthority("ROLE_DOCTOR")
                        .requestMatchers(HttpMethod.PATCH,"/api/doctor/update-password").hasAuthority("ROLE_DOCTOR")
                        .requestMatchers(HttpMethod.PATCH,"/api/cita-medica/finalizar/{id}").hasAuthority("ROLE_DOCTOR")
                        .requestMatchers("/api/horario/**").hasAuthority("ROLE_DOCTOR")
                        //*****RUTAS PERMITIDAS PARA EL ADMINISTRADOR*****//
                        .requestMatchers("/api/usuario/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/api/especialidad/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/api/doctor/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/api/dashboard/**").hasAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterAfter(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(sm-> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(cors -> cors.configurationSource(corsConfiguration()))
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfiguration(){
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:4200"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        config.setAllowedMethods(Arrays.asList("GET","POST","PUT","PATCH","DELETE"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",config);
        return source;
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
