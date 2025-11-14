package com.hospital.hospitalcitas.controllers;

import com.hospital.hospitalcitas.dtos.request.CambiarPasswordRequest;
import com.hospital.hospitalcitas.dtos.request.DoctorRequest;
import com.hospital.hospitalcitas.dtos.request.PacienteRequest;
import com.hospital.hospitalcitas.dtos.response.DoctorResponse;
import com.hospital.hospitalcitas.services.interfaces.IDoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor")
@RequiredArgsConstructor
public class DoctorController {
    private final IDoctorService doctorService;

    @GetMapping("/todos")
    public ResponseEntity<?> listarDoctores(){
        List<DoctorResponse> doctores = doctorService.findAll();
        return ResponseEntity.ok().body(doctores);
    }
    @GetMapping("/perfil")
    public ResponseEntity<?> perfilDoctor(){
        DoctorResponse doctor = doctorService.perfilDoctor();
        return ResponseEntity.ok().body(doctor);
    }
    @GetMapping()
    public ResponseEntity<?> listarDoctoresEspecialidad(@RequestParam String especialidad){
        List<DoctorResponse> doctores = doctorService.findByEspecialidad(especialidad);
        return ResponseEntity.ok().body(doctores);
    }

    @PatchMapping("/update-password")
    public ResponseEntity<?> cambiarPassowrd(@Valid @RequestBody CambiarPasswordRequest doctor) {
        doctorService.updatePassword(doctor);
        return ResponseEntity.ok(new ResponseApi("Contraseña actualizada",200));
    }

    @PostMapping
    public ResponseEntity<?> crearDoctor(@Valid @RequestBody DoctorRequest doctor){
        doctorService.save(doctor);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseApi("El doctor se ha creado", HttpStatus.CREATED.value()));
    }
}
