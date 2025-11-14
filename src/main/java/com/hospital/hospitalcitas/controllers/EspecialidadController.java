package com.hospital.hospitalcitas.controllers;

import com.hospital.hospitalcitas.dtos.request.EspecialidadRequest;
import com.hospital.hospitalcitas.dtos.response.EspecialidadResponse;
import com.hospital.hospitalcitas.services.interfaces.IEspecialidadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/especialidad")
@RequiredArgsConstructor
public class EspecialidadController {
    private final IEspecialidadService especialidadService;

    @GetMapping
    public ResponseEntity<?> listarEspecialidad() {
        List<EspecialidadResponse> especialidades = especialidadService.findAll();
        return ResponseEntity.ok().body(especialidades);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> listarEspecialidadById(@PathVariable int id) {
        Optional<EspecialidadResponse> especialidad = especialidadService.findById(id);
        return ResponseEntity.ok().body(especialidad);
    }

    @PostMapping
    public ResponseEntity<?> registrarEspecialidad(@Valid @RequestBody EspecialidadRequest especialidadRequest) {
        especialidadService.save(especialidadRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseApi("La especialidad se ha creado", HttpStatus.CREATED.value()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificarEspecialidad(@PathVariable int id, @Valid @RequestBody EspecialidadRequest especialidadRequest) {
        especialidadService.update(id, especialidadRequest);
        return ResponseEntity.ok(new ResponseApi("La especialidad se ha modificado", HttpStatus.OK.value()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarEspecialidad(@PathVariable int id) {
        especialidadService.delete(id);
        return ResponseEntity.ok(new ResponseApi("La especialidad se ha eliminado", HttpStatus.OK.value()));
    }

}
