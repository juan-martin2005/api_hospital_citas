package com.hospital.hospitalcitas.services;

import com.hospital.hospitalcitas.dtos.request.DoctorRequest;
import com.hospital.hospitalcitas.dtos.response.DoctorResponse;

import java.util.List;

public interface IDoctorService {
    List<DoctorResponse> findAll();
    List<DoctorResponse> findByEspecialidad(String especialidad);
    void save(DoctorRequest doctor);
    void update(int id, DoctorRequest doctor);


}
