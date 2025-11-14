package com.hospital.hospitalcitas.services.interfaces;

import com.hospital.hospitalcitas.dtos.request.CitaMedicaRequest;
import com.hospital.hospitalcitas.dtos.response.CitaDoctorResponse;
import com.hospital.hospitalcitas.dtos.response.CitaMedicaResponse;

import java.util.List;

public interface ICitaMedicaService {

    List<CitaMedicaResponse> findAllMyCitas();
    List<CitaDoctorResponse>  findAllCitasPaciente();
    void cancelarCitaMedica(Integer id);
    void finalizarCitaMedica(Integer id);
    void save(CitaMedicaRequest citaMedica);
}
