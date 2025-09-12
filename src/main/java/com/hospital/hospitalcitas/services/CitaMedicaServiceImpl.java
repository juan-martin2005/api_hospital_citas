package com.hospital.hospitalcitas.services;

import com.hospital.hospitalcitas.dtos.request.CitaMedicaRequest;
import com.hospital.hospitalcitas.dtos.response.CitaMedicaResponse;
import com.hospital.hospitalcitas.erros.HandlerCitaException;
import com.hospital.hospitalcitas.erros.HandlerExistException;
import com.hospital.hospitalcitas.models.*;
import com.hospital.hospitalcitas.repositories.ICitamedicaRepository;
import com.hospital.hospitalcitas.repositories.IDoctorRepository;
import com.hospital.hospitalcitas.repositories.IHorarioDoctorRepository;
import com.hospital.hospitalcitas.repositories.IPacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CitaMedicaServiceImpl implements ICitaMedicaService {

    private final IPacienteRepository pacienteRepository;
    private final IDoctorRepository doctorRepository;
    private final ICitamedicaRepository citamedicaRepository;
    private final IHorarioDoctorRepository horarioDoctorRepository;

    @Override
    public List<CitaMedicaResponse> findAllMyCitas() {
        Paciente paciente = pacienteActual();
        return citamedicaRepository.findByPaciente_Id(paciente.getId()).stream().map(CitaMedicaResponse::new).toList();
    }

    @Override
    public void save(CitaMedicaRequest citaMedica) {

        //Se busca al doctor en la db
        Doctor doctor = doctorRepository
                .findById(citaMedica.getIdDoctor())
                .orElseThrow(() -> new HandlerExistException("Doctor no encontrado"));
        //Listado de los horarios del doctor
        List<HorarioDoctor> horarioDoctor = horarioDoctorRepository
                .findAllByDoctorId(doctor.getId());

        //Buscamos el horario que le pertence al doctor escogido
        HorarioDoctor horario = horarioDoctor.stream()
                .filter(h -> h.getId() == citaMedica.getIdHorario())
                .findFirst().orElseThrow(() -> new HandlerExistException("Horario no encontrado"));

        if(horario.getEstado() == Estado.OCUPADO){
            throw new HandlerCitaException("El horario ya está en uso");
        }
        horario.setEstado(Estado.OCUPADO);
        CitaMedica citaMedicaDb = CitaMedica.builder()
                .doctor(doctor)
                .paciente(pacienteActual())
                .estado(Estado.PENDIENTE)
                .horario(horario)
                .build();
        citamedicaRepository.save(citaMedicaDb);
    }


    public Paciente pacienteActual() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Paciente paciente = pacienteRepository.findByUsuario_Username(username).orElseThrow();
        System.out.println(username);
        return paciente;
    }
}
