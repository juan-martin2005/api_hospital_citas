package com.hospital.hospitalcitas.controllers;

import com.hospital.hospitalcitas.dtos.request.PacienteRequest;
import com.hospital.hospitalcitas.models.Paciente;
import com.hospital.hospitalcitas.services.IPacienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/paciente")
public class PacienteController {

    private final IPacienteService pacienteService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(pacienteService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        return ResponseEntity.ok(pacienteService.findById(id));
    }
    @PostMapping
    public ResponseEntity<?> crearPaciente(@Valid @RequestBody PacienteRequest paciente) {
        pacienteService.save(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseApi("Paciente creado",201));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarContrasena(@PathVariable int id, @Valid @RequestBody PacienteRequest paciente) {
        pacienteService.update(id, paciente);
        return ResponseEntity.ok(new ResponseApi("Contraseña actualizada",200));
    }
}
