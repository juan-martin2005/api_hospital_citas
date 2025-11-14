package com.hospital.hospitalcitas.services.impls;

import com.hospital.hospitalcitas.dtos.request.HorarioDoctorRequest;
import com.hospital.hospitalcitas.dtos.response.HorarioDoctorResponse;
import com.hospital.hospitalcitas.erros.HandlerDateTimeException;
import com.hospital.hospitalcitas.erros.HandlerExistException;
import com.hospital.hospitalcitas.models.Doctor;
import com.hospital.hospitalcitas.models.Estado;
import com.hospital.hospitalcitas.models.HorarioDoctor;
import com.hospital.hospitalcitas.repositories.IDoctorRepository;
import com.hospital.hospitalcitas.repositories.IHorarioDoctorRepository;
import com.hospital.hospitalcitas.services.interfaces.IHorarioDoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HorarioDoctorServiceImpl implements IHorarioDoctorService {
    private final IHorarioDoctorRepository  horarioDoctorRepository;
    private final IDoctorRepository doctorRepository;

    @Override
    public List<HorarioDoctorResponse> findAllByDoctor() {
        Doctor doctor = doctorActual();
        return horarioDoctorRepository.findAllByDoctorId(doctor.getId()).stream().map(HorarioDoctorResponse::new).toList();
    }

    @Override
    public void save(HorarioDoctorRequest horarioDoctor) {
        Doctor doctor = doctorActual();
        validacionFechaYHora(doctor.getId(), horarioDoctor);
        HorarioDoctor horario = HorarioDoctor.builder()
                .fecha(horarioDoctor.getFecha())
                .horaInicio(horarioDoctor.getHoraInicio())
                .horaFin(horarioDoctor.getHoraFin())
                .doctor(doctor)
                .build();
        horarioDoctorRepository.save(horario);
    }

    @Override
    public void update(int id, HorarioDoctorRequest horarioDoctor) {
        Optional<HorarioDoctor> oHorario = horarioDoctorRepository.findById(id);
        HorarioDoctor horarioDb = oHorario.orElseThrow(() -> new HandlerExistException("El horario no existe!"));
        Doctor doctor = doctorActual();
        validacionFechaYHora(doctor.getId(), horarioDoctor);

        horarioDb.setFecha(horarioDoctor.getFecha());
        horarioDb.setHoraInicio(horarioDoctor.getHoraInicio());
        horarioDb.setHoraFin(horarioDoctor.getHoraFin());
        horarioDoctorRepository.save(horarioDb);
    }

    @Override
    public void delete(int id) {
        //*****No se puede eliminar si el horario está registrado para un cita***** (VALIDAR!)//
        HorarioDoctor horario= horarioDoctorRepository.findById(id).orElseThrow(() -> new HandlerExistException("El horario no existe!"));
        if(horario.getEstado() != Estado.LIBRE)
            throw new HandlerExistException("El horario está asignado a un paciente, no se puede elimar!");
        horarioDoctorRepository.deleteById(id);
    }

    public Doctor doctorActual() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Doctor doctor = doctorRepository.findByUsuario_Username(username).orElseThrow();
        System.out.println(username);
        return doctor;
    }
    public void validacionFechaYHora(int doctorId, HorarioDoctorRequest horarioDoctor){
        List<HorarioDoctor> horarios = horarioDoctorRepository
                .findByDoctorIdAndFechaAndHoraInicioAndHoraFin(
                        doctorId,
                        horarioDoctor.getFecha(),
                        horarioDoctor.getHoraInicio(),
                        horarioDoctor.getHoraFin()
                );
        boolean isFechaNoValida = horarioDoctor.getFecha().isBefore(LocalDate.now());
        boolean isFechaHoy = horarioDoctor.getFecha().equals(LocalDate.now());
        boolean isHorarioNoPermitido = horarioDoctor.getHoraInicio().isBefore(LocalTime.of(7, 30)) || horarioDoctor.getHoraFin().isAfter(LocalTime.of(17, 30));
        boolean isHorarioInvalido = horarioDoctor.getHoraFin().isBefore(horarioDoctor.getHoraInicio()) || horarioDoctor.getHoraInicio().isAfter(horarioDoctor.getHoraFin());
        boolean isTiempoPermitido = Duration.between(horarioDoctor.getHoraInicio(), horarioDoctor.getHoraFin()).toMinutes() == 30;

        if(isFechaNoValida){
            throw new HandlerDateTimeException("No se puede crear horarios en fecha pasada");
        }
        if(isFechaHoy){
            throw new HandlerDateTimeException("La fecha debe ser para el día siguiente");
        }
        //Horas validas de 7:30 hasta las 17:30
        if(isHorarioNoPermitido || isHorarioInvalido ){
            throw new HandlerDateTimeException("No se puede crear el horario en las horas seleccionadas");
        }
        //30 minutos para cada horario
        if(!isTiempoPermitido){
            throw new HandlerDateTimeException("El tiempo máximo para cada horario es de 30 minutos");
        }
        if(!horarios.isEmpty()){
            throw new HandlerDateTimeException("La hora ya existe, en esa fecha");
        }
    }

}
