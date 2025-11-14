package com.hospital.hospitalcitas.dtos.response;

import com.hospital.hospitalcitas.models.Doctor;
import com.hospital.hospitalcitas.models.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class DoctorResponse {
    private int id;
    private String nombre;
    private String apellido;
    private String especialidad;
    private String email;
    private String telefono;
    private char sexo;
    private String estado;
    private List<HorarioDoctorResponse> horarios;

    public DoctorResponse(Doctor doctor) {
        this.id = doctor.getId();
        this.nombre = doctor.getUsuario().getNombre();
        this.apellido = doctor.getUsuario().getApellido();
        this.especialidad = doctor.getEspecialidad().getNombre();
        this.email = doctor.getEmail();
        this.telefono = doctor.getTelefono();
        this.sexo = doctor.getSexo();
        this.horarios = doctor.getHorarios().stream().map(HorarioDoctorResponse::new).toList();
    }
}
