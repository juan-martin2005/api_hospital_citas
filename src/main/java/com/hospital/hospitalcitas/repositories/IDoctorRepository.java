package com.hospital.hospitalcitas.repositories;

import com.hospital.hospitalcitas.models.Doctor;
import com.hospital.hospitalcitas.models.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface IDoctorRepository extends JpaRepository<Doctor, Integer> {
    Optional<Doctor> findByUsuario_Username(String username);
    List<Doctor> findByEspecialidad_Nombre(String especialidad);

}
