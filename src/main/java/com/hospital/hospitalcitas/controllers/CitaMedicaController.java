package com.hospital.hospitalcitas.controllers;

import com.hospital.hospitalcitas.dtos.request.CitaMedicaRequest;
import com.hospital.hospitalcitas.models.CitaMedica;
import com.hospital.hospitalcitas.services.ICitaMedicaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/cita-medica")
public class CitaMedicaController {

    private final ICitaMedicaService citaMedicaService;

    @GetMapping
    public ResponseEntity<?> listarMisCitas(){
        return ResponseEntity.ok(citaMedicaService.findAllMyCitas());
    }

    @PostMapping
    public ResponseEntity<?> reservarCitaMedica(@Valid @RequestBody CitaMedicaRequest citaMedica){
        citaMedicaService.save(citaMedica);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseApi("Cita médica reservada",201));
    }

}
