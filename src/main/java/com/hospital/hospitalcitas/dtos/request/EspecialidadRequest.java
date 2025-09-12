package com.hospital.hospitalcitas.dtos.request;

import com.hospital.hospitalcitas.models.Especialidad;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EspecialidadRequest {
    @NotBlank(message = "el campo nombre no debe estar vacío")
    private String nombre;
    @NotBlank(message = "el campo descripcion no debe estar vacío")
    private String descripcion;

}
