package com.hospital.hospitalcitas.dtos.request;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class PacienteRequest {

    @Pattern(regexp = "^[0-9]{8}$", message = "el formato del dni es incorrecto")
    @Size(min = 8, max = 8, message = "el dni debe tener 8 dígitos")
    private String dni;

    @NotBlank(message = "el campo email no debe estar vacío")
    @Email(message = "el formato del email no es válido")
    private String email;

    @NotBlank(message = "el campo password no debe estar vacío")
    private String password;

    @NotBlank(message = "el campo nombre no debe estar vacío")
    private String nombre;

    @NotBlank(message = "el campo apellido no debe estar vacío")
    private String apellido;

    @NotNull(message = "el campo fecha de nacimiento no debe estar vacío")
    @Past(message = "la fecha de nacimiento debe ser pasado")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;

    @NotBlank(message = "el campo telefono no debe estar vacío")
    @Pattern(regexp = "^[0-9]{9}$", message = "el formato del telefono es incorrecto")
    @Size(min = 9, max = 9, message = "el telefono debe tener 9 dígitos")
    private String telefono;

    @NotBlank(message = "el campo sexo no debe estar vacío")
    @Size(min = 1, max = 1, message = "solo se acepta un caracter")
    @Pattern(regexp = "[MFmf]", message = "debe ser : M o F")
    private String sexo;

}
