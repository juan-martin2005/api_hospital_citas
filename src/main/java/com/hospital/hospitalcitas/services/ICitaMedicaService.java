package com.hospital.hospitalcitas.services;

import com.hospital.hospitalcitas.dtos.request.CitaMedicaRequest;
import com.hospital.hospitalcitas.dtos.response.CitaMedicaResponse;

import java.util.List;

public interface ICitaMedicaService {

    List<CitaMedicaResponse> findAllMyCitas();
    void save(CitaMedicaRequest citaMedica);


}
