package com.hospital.hospitalcitas.services.interfaces;

import com.hospital.hospitalcitas.dtos.request.CambiarPasswordRequest;
import com.hospital.hospitalcitas.dtos.request.DoctorRequest;
import com.hospital.hospitalcitas.dtos.response.DoctorResponse;

import java.util.List;

public interface IDoctorService {
    List<DoctorResponse> findAll();
    List<DoctorResponse> findByEspecialidad(String especialidad);
    DoctorResponse perfilDoctor();
    void save(DoctorRequest doctor);
    void updatePassword(CambiarPasswordRequest doctor);


}
