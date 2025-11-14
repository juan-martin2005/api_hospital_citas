package com.hospital.hospitalcitas.services.interfaces;

import com.hospital.hospitalcitas.dtos.request.ActualizarPerfilRequest;
import com.hospital.hospitalcitas.dtos.request.CambiarPasswordRequest;
import com.hospital.hospitalcitas.dtos.request.PacienteRequest;
import com.hospital.hospitalcitas.dtos.response.PacienteResponse;

import java.util.List;

public interface IPacienteService {
    List<PacienteResponse> findAll();
    PacienteResponse findById(int id);
    PacienteResponse perfilPaciente();
    void save(PacienteRequest paciente);
    void update(ActualizarPerfilRequest paciente);
    void updatePassword(CambiarPasswordRequest paciente);
}
