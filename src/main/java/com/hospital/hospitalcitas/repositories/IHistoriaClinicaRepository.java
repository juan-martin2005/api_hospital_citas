package com.hospital.hospitalcitas.repositories;

import com.hospital.hospitalcitas.models.HistoriaClinica;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IHistoriaClinicaRepository extends JpaRepository<HistoriaClinica, Integer> {
    List<HistoriaClinica> findByPaciente_Id(Integer pacienteId);
    HistoriaClinica findByCita_Id(Integer citaId);
}

