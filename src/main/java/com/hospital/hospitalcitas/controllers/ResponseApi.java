package com.hospital.hospitalcitas.controllers;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseApi {
    private String mensaje;
    private int status;
    private LocalDateTime fecha;
    public ResponseApi(String mensaje, int status) {
        this.mensaje = mensaje;
        this.status = status;
        this.fecha = LocalDateTime.now();
    }

}
