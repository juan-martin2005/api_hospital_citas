package com.hospital.hospitalcitas.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CitaMedicaRequest {
    @NotNull(message = "el campo idDoctor no debe estar vacío")
    private Integer idDoctor;
    @NotNull(message = "el campo idHorario no debe estar vacío")
    private Integer idHorario;
}
