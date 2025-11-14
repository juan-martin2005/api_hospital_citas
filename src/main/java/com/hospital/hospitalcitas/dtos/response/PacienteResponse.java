package com.hospital.hospitalcitas.dtos.response;

import com.hospital.hospitalcitas.models.Paciente;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;

@Getter
@Setter
@NoArgsConstructor
public class PacienteResponse {

    private Integer id;
    private String dni;
    private String email;
    private String nombre;
    private String apellido;
    private String telefono;
    private char sexo;
    private LocalDate fechaNacimiento;
    private Integer edad;

    public PacienteResponse(Paciente paciente) {
        this.id = paciente.getId();
        this.dni = paciente.getDni();
        this.email = paciente.getEmail();
        this.nombre = paciente.getUsuario().getNombre();
        this.apellido = paciente.getUsuario().getApellido();
        this.telefono = paciente.getTelefono();
        this.sexo = paciente.getSexo();
        this.fechaNacimiento = paciente.getFechaNacimiento();
        this.edad = Period.between(paciente.getFechaNacimiento(), LocalDate.now()).getYears();
    }
}
