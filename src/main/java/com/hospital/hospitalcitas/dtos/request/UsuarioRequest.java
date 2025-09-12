package com.hospital.hospitalcitas.dtos.request;

import com.hospital.hospitalcitas.models.Role;
import com.hospital.hospitalcitas.models.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioRequest {
    @NotBlank(message = "el campo username no debe estar vacío")
    private String username;
    @NotBlank(message = "el campo password no debe estar vacío")
    private String password;
    @NotBlank(message = "el campo nombre no debe estar vacío")
    private String nombre;
    @NotBlank(message = "el campo apellido no debe estar vacío")
    private String apellido;
    @NotNull(message = "el campo rol no debe estar vacío")
    private List<Role> roles;

}
