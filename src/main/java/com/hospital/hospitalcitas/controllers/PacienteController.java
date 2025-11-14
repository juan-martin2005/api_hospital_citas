package com.hospital.hospitalcitas.controllers;

import com.hospital.hospitalcitas.dtos.request.ActualizarPerfilRequest;
import com.hospital.hospitalcitas.dtos.request.CambiarPasswordRequest;
import com.hospital.hospitalcitas.dtos.request.PacienteRequest;
import com.hospital.hospitalcitas.dtos.response.PacienteResponse;
import com.hospital.hospitalcitas.services.interfaces.IPacienteService;
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

    @GetMapping("/perfil")
    public ResponseEntity<?> perfilPaciente(){
        PacienteResponse paciente = pacienteService.perfilPaciente();
        return ResponseEntity.ok().body(paciente);
    }

    @PostMapping
    public ResponseEntity<?> crearPaciente(@Valid @RequestBody PacienteRequest paciente) {
        pacienteService.save(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseApi("Paciente creado",201));
    }

    @PutMapping("/update-perfil")
    public ResponseEntity<?> editarPerfil( @Valid @RequestBody ActualizarPerfilRequest paciente) {
        pacienteService.update(paciente);
        return ResponseEntity.ok(new ResponseApi("Contraseña actualizada",200));
    }
    @PatchMapping("/update-password")
    public ResponseEntity<?> cambiarPassword(@Valid @RequestBody CambiarPasswordRequest paciente) {
        pacienteService.updatePassword(paciente);
        return ResponseEntity.ok(new ResponseApi("Contraseña actualizada",200));
    }
}
