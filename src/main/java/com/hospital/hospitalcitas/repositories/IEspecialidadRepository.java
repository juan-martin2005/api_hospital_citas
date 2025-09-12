package com.hospital.hospitalcitas.repositories;

import com.hospital.hospitalcitas.models.Especialidad;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IEspecialidadRepository extends JpaRepository<Especialidad, Integer> {
    boolean existsByNombre(String nombre);

    Optional<Especialidad> findByNombre(String especialidad);
}
