package com.hospital.hospitalcitas.repositories;

import com.hospital.hospitalcitas.models.Estado;
import com.hospital.hospitalcitas.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
    boolean existsByUsername(String username);
    Optional<Usuario> findByUsername(String username);
    boolean existsByUsernameAndEstado(String username, Estado estado);

}
