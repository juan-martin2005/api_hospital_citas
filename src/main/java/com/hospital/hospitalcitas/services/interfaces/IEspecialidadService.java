package com.hospital.hospitalcitas.services.interfaces;

import com.hospital.hospitalcitas.dtos.request.EspecialidadRequest;
import com.hospital.hospitalcitas.dtos.response.EspecialidadResponse;

import java.util.List;
import java.util.Optional;

public interface IEspecialidadService {
    List<EspecialidadResponse> findAll();
    Optional<EspecialidadResponse> findById(int id);
    void save(EspecialidadRequest especialidad);
    void update(int id, EspecialidadRequest especialidad);
    void delete(int id);
}
