package com.hospital.hospitalcitas.dtos.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hospital.hospitalcitas.models.CitaMedica;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class CitaMedicaResponse {

    private int id;
    private String nombreDoctor;
    private String especialidadDoctor;
    private LocalDate fecha;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaInicio;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaFin;
    private String estado;


    public CitaMedicaResponse(CitaMedica citaMedica) {
        this.id = citaMedica.getId();
        this.nombreDoctor = citaMedica.getDoctor().getUsuario().getNombre();
        this.especialidadDoctor = citaMedica.getDoctor().getEspecialidad().getNombre();
        this.fecha = citaMedica.getHorario().getFecha();
        this.horaInicio = citaMedica.getHorario().getHoraInicio();
        this.horaFin = citaMedica.getHorario().getHoraFin();
        this.estado = citaMedica.getEstado().toString();
    }
}
