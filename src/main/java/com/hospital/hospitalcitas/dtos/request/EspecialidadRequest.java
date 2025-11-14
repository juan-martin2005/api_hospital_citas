package com.hospital.hospitalcitas.dtos.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class EspecialidadRequest {
    @NotBlank(message = "el campo nombre no debe estar vacío")
    @Pattern(regexp = "[a-zA-ZáéíóúÁÉÍÓÚñÑ ]++", message = "Solo se aceptan cadenas de texto")
    @Size(min = 6, message = "debe tener 6 caracteres minimo")
    private String nombre;
    @NotBlank(message = "el campo descripcion no debe estar vacío")
    @Pattern(regexp = "[a-zA-ZáéíóúÁÉÍÓÚñÑ,. ]+", message = "Solo se aceptan cadenas de texto")
    @Size(min = 10, message = "debe tener 10 caracteres minimo")
    private String descripcion;
    @NotNull(message = "el campo precio no debe estar vacío")
    @DecimalMin(value = "1.00", message = "el precio debe ser mayor que 0")
    private BigDecimal precio;

}
