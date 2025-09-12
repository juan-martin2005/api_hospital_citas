package com.hospital.hospitalcitas.services;

import com.hospital.hospitalcitas.dtos.request.PacienteRequest;
import com.hospital.hospitalcitas.dtos.response.PacienteResponse;

import java.util.List;

public interface IPacienteService {
    List<PacienteResponse> findAll();
    PacienteResponse findById(int id);
    void save(PacienteRequest paciente);
    void update(int id, PacienteRequest paciente);
}
