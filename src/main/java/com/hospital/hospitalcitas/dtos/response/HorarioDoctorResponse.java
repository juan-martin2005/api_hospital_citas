package com.hospital.hospitalcitas.dtos.response;

import com.hospital.hospitalcitas.models.HorarioDoctor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
public class HorarioDoctorResponse {
    private int id;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    public HorarioDoctorResponse(HorarioDoctor horarioDoctor) {
        this.id = horarioDoctor.getId();
        this.fecha = horarioDoctor.getFecha();
        this.horaInicio = horarioDoctor.getHoraInicio();
        this.horaFin = horarioDoctor.getHoraFin();
    }

}
