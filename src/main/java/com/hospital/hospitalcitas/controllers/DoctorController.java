package com.hospital.hospitalcitas.controllers;

import com.hospital.hospitalcitas.dtos.request.DoctorRequest;
import com.hospital.hospitalcitas.dtos.response.DoctorResponse;
import com.hospital.hospitalcitas.services.IDoctorService;
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

    @GetMapping
    public ResponseEntity<?> listarDoctores(){
        List<DoctorResponse> doctores = doctorService.findAll();
        return ResponseEntity.ok().body(doctores);
    }
    @GetMapping("/{especialidad}")
    public ResponseEntity<?> listarDoctoresEspecialidad(@PathVariable String especialidad){
        List<DoctorResponse> doctores = doctorService.findByEspecialidad(especialidad);
        return ResponseEntity.ok().body(doctores);
    }

    @PostMapping
    public ResponseEntity<?> crearDoctor(@Valid @RequestBody DoctorRequest doctor){
        doctorService.save(doctor);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseApi("El doctor se ha creado", HttpStatus.CREATED.value()));
    }
}
