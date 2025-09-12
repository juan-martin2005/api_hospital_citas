package com.hospital.hospitalcitas.repositories;

import com.hospital.hospitalcitas.dtos.request.CitaMedicaRequest;
import com.hospital.hospitalcitas.dtos.response.CitaMedicaResponse;
import com.hospital.hospitalcitas.models.CitaMedica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICitamedicaRepository extends  JpaRepository<CitaMedica, Integer> {
    List<CitaMedica> findByPaciente_Id(Integer id);
}
