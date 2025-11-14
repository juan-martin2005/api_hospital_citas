package com.hospital.hospitalcitas.services.interfaces;

import com.hospital.hospitalcitas.dtos.request.UsuarioRequest;
import com.hospital.hospitalcitas.dtos.response.UsuarioResponse;

import java.util.List;

public interface IUsuarioService {
    List<UsuarioResponse> findAll();
    void save(UsuarioRequest usuario);
    void update(int id, UsuarioRequest usuario);
    void activarUser(int id);
    void softDelete(int id);
}
