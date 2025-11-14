package com.hospital.hospitalcitas.dtos.request;

import com.hospital.hospitalcitas.models.Doctor;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DoctorRequest {
    @NotBlank(message = "el campo email no debe estar vacío")
    @Email(message = "el formato del email no es válido")
    @Pattern(regexp = "^[a-zA-ZáéíóúñÁÉÍÓÚÑ0-9-._]+@hospital\\.com$", message = "el formato del email no es válido, debe terminar con '@hospital.com'")
    private String email;
    @NotBlank(message = "el campo username no debe estar vacío")
    @Size(min = 8, message = "el password debe tener 8 caracteres como mínimo")
    private String password;
    @NotBlank(message = "el campo nombre no debe estar vacío")
    @Pattern(regexp = "[a-zA-ZáéíóúñÁÉÍÓÚÑ ]+", message = "solo se aceptan cadenas de texto")
    private String nombre;
    @NotBlank(message = "el campo apellido no debe estar vacío")
    @Pattern(regexp = "[a-zA-ZáéíóúñÁÉÍÓÚÑ ]+", message = "solo se aceptan cadenas de texto")
    private String apellido;

    @NotBlank(message = "el campo telefono no debe estar vacío")
    @Pattern(regexp = "^[0-9]{9}$", message = "el formato del telefono es incorrecto")
    @Size(min = 9, max = 9, message = "el telefono debe tener 9 dígitos")
    private String telefono;

    @NotBlank(message = "el campo sexo no debe estar vacío")
    @Size(min = 1, max = 1, message = "solo se acepta un caracter")
    @Pattern(regexp = "[MFmf]", message = "debe ser : M o F")
    private String sexo;
    @NotBlank(message = "el campo especialidad no debe estar vacío")
    private String especialidad;

}
