package com.hospital.hospitalcitas.dtos.response;

import com.hospital.hospitalcitas.models.Especialidad;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class EspecialidadResponse {
    private int id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    public EspecialidadResponse(Especialidad especialidad) {
        this.id = especialidad.getId();
        this.nombre = especialidad.getNombre();
        this.descripcion = especialidad.getDescripcion();
        this.precio = especialidad.getPrecio();
    }
}
