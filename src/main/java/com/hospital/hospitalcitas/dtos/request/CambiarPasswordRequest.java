package com.hospital.hospitalcitas.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CambiarPasswordRequest {
    @NotBlank(message = "el campo password no debe estar vacío")
    @Size(min = 8, message = "el password debe tener 8 caracteres como mínimo")
    private String password;
}
