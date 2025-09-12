package com.hospital.hospitalcitas.services;

import com.hospital.hospitalcitas.dtos.request.UsuarioRequest;
import com.hospital.hospitalcitas.dtos.response.UsuarioResponse;
import com.hospital.hospitalcitas.erros.HandlerExistException;
import com.hospital.hospitalcitas.models.Estado;
import com.hospital.hospitalcitas.models.Role;
import com.hospital.hospitalcitas.models.Usuario;
import com.hospital.hospitalcitas.repositories.IRoleRepository;
import com.hospital.hospitalcitas.repositories.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements IUsuarioService {

    private final IUsuarioRepository usuarioRepository;
    private final IRoleRepository  roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UsuarioResponse> findAll() {
        List<UsuarioResponse> usuarios = usuarioRepository.findAll().stream().map(UsuarioResponse::new).toList();
        if(usuarios.isEmpty()){
          throw new NullPointerException();
        }
        return usuarios;
    }

    @Override
    public void save(UsuarioRequest usuario) {
        Optional<Role> optRole = roleRepository.findByNombre("");
        Set<Role> roles = new HashSet<>();
        optRole.ifPresent(roles::add);
        if(usuarioRepository.existsByUsername(usuario.getUsername())){
            throw new HandlerExistException("El nombre de usuario ya existe");
        }
        String password = passwordEncoder.encode(usuario.getPassword());
        Usuario usuarioDb = Usuario.builder()
                .username(usuario.getUsername())
                .password(password)
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .rol(roles)
                .build();
        usuarioRepository.save(usuarioDb);
    }

    @Override
    public void update(int id, UsuarioRequest usuarioInp) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        Usuario usuarioDb = usuario.orElseThrow(()-> new HandlerExistException("El usuario no existe"));
        usuarioDb.setPassword(usuarioInp.getPassword());
        usuarioRepository.save(usuarioDb);
    }

    @Override
    public void softDelete(int id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        Usuario usuarioDb = usuario.orElseThrow(()-> new HandlerExistException("El usuario no existe"));
        if(usuarioDb.getEstado() == Estado.INACTIVO){
             throw new HandlerExistException("El usuario se encuentra inactivo");
        }
        usuarioDb.setEstado(Estado.INACTIVO);
        usuarioRepository.save(usuarioDb);
    }
}
