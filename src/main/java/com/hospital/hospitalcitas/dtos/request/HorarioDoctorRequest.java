package com.hospital.hospitalcitas.dtos.request;

import com.hospital.hospitalcitas.models.HorarioDoctor;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HorarioDoctorRequest {
    @NotNull(message = "el campo fecha no debe estar vacio")
    private LocalDate fecha;
    @NotNull(message = "el campo horaInicio no debe estar vacio")
    private LocalTime horaInicio;
    @NotNull(message = "el campo horaFin no debe estar vacio")
    private LocalTime horaFin;

}
