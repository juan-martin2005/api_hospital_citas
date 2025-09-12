package com.hospital.hospitalcitas.services;

import com.hospital.hospitalcitas.dtos.request.EspecialidadRequest;
import com.hospital.hospitalcitas.dtos.response.EspecialidadResponse;

import java.util.List;

public interface IEspecialidadService {
    List<EspecialidadResponse> findAll();
    void save(EspecialidadRequest especialidad);
    void update(int id, EspecialidadRequest especialidad);
    void delete(int id);
}
