package com.hospital.hospitalcitas.repositories;

import com.hospital.hospitalcitas.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IPacienteRepository extends JpaRepository<Paciente, Integer> {
    Optional<Paciente> findByUsuario_Username(String username);
}
