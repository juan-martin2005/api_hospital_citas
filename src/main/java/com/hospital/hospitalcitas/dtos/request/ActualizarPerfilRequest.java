package com.hospital.hospitalcitas.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ActualizarPerfilRequest {
    @NotBlank(message = "el campo email no debe estar vacío")
    @Email(message = "el formato del email no es válido")
    private String email;

    @NotBlank(message = "el campo telefono no debe estar vacío")
    @Pattern(regexp = "^[0-9]{9}$", message = "el formato del telefono es incorrecto")
    @Size(min = 9, max = 9, message = "el telefono debe tener 9 dígitos")
    private String telefono;
}
