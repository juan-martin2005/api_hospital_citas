package com.hospital.hospitalcitas.dtos.response;

import com.hospital.hospitalcitas.models.CitaMedica;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;

@Getter
@Setter
@NoArgsConstructor
public class CitaDoctorResponse {

    private int id;
    private String dniPaciente;
    private String nombrePaciente;
    private String apellidoPaciente;
    private String telefonoPaciente;
    private char sexoPaciente;
    private Integer edadPaciente;
    private String emailPaciente;
    private HorarioDoctorResponse horario;
    private String estado;

    public CitaDoctorResponse(CitaMedica citaMedica) {
        this.id = citaMedica.getId();
        this.nombrePaciente = citaMedica.getPaciente().getUsuario().getNombre();
        this.apellidoPaciente = citaMedica.getPaciente().getUsuario().getApellido();
        this.dniPaciente = citaMedica.getPaciente().getDni();
        this.telefonoPaciente = citaMedica.getPaciente().getTelefono();
        this.sexoPaciente = citaMedica.getPaciente().getSexo();
        this.edadPaciente = Period.between(citaMedica.getPaciente().getFechaNacimiento(), LocalDate.now()).getYears();
        this.emailPaciente = citaMedica.getPaciente().getEmail();
        this.horario = new HorarioDoctorResponse(citaMedica.getHorario());
        this.estado = citaMedica.getEstado().toString();
    }
}
