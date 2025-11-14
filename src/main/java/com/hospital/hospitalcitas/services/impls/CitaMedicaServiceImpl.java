package com.hospital.hospitalcitas.services.impls;

import com.hospital.hospitalcitas.dtos.request.CitaMedicaRequest;
import com.hospital.hospitalcitas.dtos.response.CitaDoctorResponse;
import com.hospital.hospitalcitas.dtos.response.CitaMedicaResponse;
import com.hospital.hospitalcitas.erros.HandlerCitaException;
import com.hospital.hospitalcitas.erros.HandlerExistException;
import com.hospital.hospitalcitas.models.*;
import com.hospital.hospitalcitas.repositories.ICitamedicaRepository;
import com.hospital.hospitalcitas.repositories.IDoctorRepository;
import com.hospital.hospitalcitas.repositories.IHorarioDoctorRepository;
import com.hospital.hospitalcitas.repositories.IPacienteRepository;
import com.hospital.hospitalcitas.services.interfaces.ICitaMedicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CitaMedicaServiceImpl implements ICitaMedicaService {

    private final IPacienteRepository pacienteRepository;
    private final IDoctorRepository doctorRepository;
    private final ICitamedicaRepository citamedicaRepository;
    private final IHorarioDoctorRepository horarioDoctorRepository;

    @Override
    public List<CitaDoctorResponse> findAllCitasPaciente() {
        Doctor doctor = doctorActual();
        return citamedicaRepository.findByDoctor_id(doctor.getId()).stream().map(CitaDoctorResponse::new).toList();
    }

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

    @Override
    public void cancelarCitaMedica(Integer id) {
        Paciente paciente = pacienteActual();
        CitaMedica cita = citamedicaRepository.findByPaciente_IdAndId(id, paciente.getId()).orElseThrow(() -> new HandlerExistException("La cita no se ha encontrado"));
        cita.setEstado(Estado.CANCELADO);
        citamedicaRepository.save(cita);
    }

    @Override
    public void finalizarCitaMedica(Integer id) {
        Doctor doctor = doctorActual();
        CitaMedica cita = citamedicaRepository.findByDoctor_IdAndId(doctor.getId(),id).orElseThrow(() -> new HandlerExistException("La cita no se ha encontrado"));
        cita.setEstado(Estado.FINALIZADO);
        citamedicaRepository.save(cita);

    }

    public Paciente pacienteActual() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Paciente paciente = pacienteRepository.findByUsuario_Username(username).orElseThrow();
        System.out.println(username);
        return paciente;
    }
    public Doctor doctorActual() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Doctor doctor = doctorRepository.findByUsuario_Username(username).orElseThrow();
        System.out.println(username);
        return doctor;
    }

}
