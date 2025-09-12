package com.hospital.hospitalcitas.services;

import com.hospital.hospitalcitas.dtos.request.UsuarioRequest;
import com.hospital.hospitalcitas.dtos.response.UsuarioResponse;

import java.util.List;

public interface IUsuarioService {
    List<UsuarioResponse> findAll();
    void save(UsuarioRequest usuario);
    void update(int id, UsuarioRequest usuario);
    void softDelete(int id);
}
