package com.hospital.hospitalcitas.dtos.response;

import com.hospital.hospitalcitas.models.Role;
import com.hospital.hospitalcitas.models.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponse {
    private int id;
    private String nombre;
    private String apellido;
    private String username;
    private Set<Role> roles;
    private String estado;

    public UsuarioResponse(Usuario usuario) {
        this.id = usuario.getId();
        this.nombre = usuario.getNombre();
        this.apellido = usuario.getApellido();
        this.username = usuario.getUsername();
        this.roles = usuario.getRol();
        this.estado = usuario.getEstado().toString();
    }
}
