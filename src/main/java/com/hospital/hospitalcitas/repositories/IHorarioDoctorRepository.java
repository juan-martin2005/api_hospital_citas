package com.hospital.hospitalcitas.repositories;

import com.hospital.hospitalcitas.models.HorarioDoctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface IHorarioDoctorRepository extends JpaRepository<HorarioDoctor, Integer> {
    List<HorarioDoctor> findAllByDoctorId(int id);
    List<HorarioDoctor> findByDoctorIdAndFechaAndHoraInicioAndHoraFin(
            int doctorId,
            LocalDate fecha,
            LocalTime horaInicio,
            LocalTime horaFin
    );}
