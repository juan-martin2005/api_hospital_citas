package com.hospital.hospitalcitas.dtos.response;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaInicio;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaFin;
    private String estado;

    public HorarioDoctorResponse(HorarioDoctor horarioDoctor) {
        this.id = horarioDoctor.getId();
        this.fecha = horarioDoctor.getFecha();
        this.horaInicio = horarioDoctor.getHoraInicio();
        this.horaFin = horarioDoctor.getHoraFin();
        this.estado = horarioDoctor.getEstado().toString();
    }

}
