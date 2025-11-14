package com.hospital.hospitalcitas.services.interfaces;

import com.hospital.hospitalcitas.dtos.request.HorarioDoctorRequest;
import com.hospital.hospitalcitas.dtos.response.HorarioDoctorResponse;

import java.util.List;

public interface IHorarioDoctorService {
    List<HorarioDoctorResponse> findAllByDoctor();
    void save(HorarioDoctorRequest horarioDoctor);
    void update(int id, HorarioDoctorRequest horarioDoctor);
    void delete(int id);
}
