package com.hospital.hospitalcitas.repositories;

import com.hospital.hospitalcitas.models.Role;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByNombre(String nombre);

}
