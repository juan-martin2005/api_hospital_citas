package com.hospital.hospitalcitas.dtos.response;

import com.hospital.hospitalcitas.models.Especialidad;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EspecialidadResponse {
    private int id;
    private String nombre;
    private String descripcion;
    public EspecialidadResponse(Especialidad especialidad) {
        this.id = especialidad.getId();
        this.nombre = especialidad.getNombre();
        this.descripcion = especialidad.getDescripcion();
    }
}
