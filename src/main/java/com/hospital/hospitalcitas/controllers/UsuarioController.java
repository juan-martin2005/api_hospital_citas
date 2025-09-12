package com.hospital.hospitalcitas.controllers;

import com.hospital.hospitalcitas.dtos.request.UsuarioRequest;
import com.hospital.hospitalcitas.dtos.response.UsuarioResponse;
import com.hospital.hospitalcitas.jwt.JwtService;
import com.hospital.hospitalcitas.services.IUsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    //Injecciones de dependencias
    private final IUsuarioService usuarioService;

    //Rutas GET de la API
    @GetMapping
    public ResponseEntity<?> listarUsuarios(){
        List<UsuarioResponse> usuarios =  usuarioService.findAll();
        return ResponseEntity.ok().body(usuarios);
    }

    //Rutas PUT de la API
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarUsuario(@Valid @PathVariable int id, @RequestBody UsuarioRequest usuarioDto){
        usuarioService.update(id,usuarioDto);
        return ResponseEntity.ok(new ResponseApi("El usuario se ha modificado", HttpStatus.OK.value()));
    }

    //Rutas DELETE de la API
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable int id){
        usuarioService.softDelete(id);
        return ResponseEntity.ok(new ResponseApi("El usuario se ha eliminado", HttpStatus.OK.value()));
    }


}
