package com.hospital.hospitalcitas.controllers;

import com.hospital.hospitalcitas.dtos.request.CitaMedicaRequest;
import com.hospital.hospitalcitas.services.interfaces.ICitaMedicaService;
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

    @GetMapping("/pacientes")
    public ResponseEntity<?> listarCitasPaciente() {
        return ResponseEntity.ok(citaMedicaService.findAllCitasPaciente());
    }
    @GetMapping("/mis-citas")
    public ResponseEntity<?> listarMisCitas(){
        return ResponseEntity.ok(citaMedicaService.findAllMyCitas());
    }

    @PostMapping
    public ResponseEntity<?> reservarCitaMedica(@Valid @RequestBody CitaMedicaRequest citaMedica){
        citaMedicaService.save(citaMedica);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseApi("Cita médica reservada",201));
    }

    @PatchMapping("cancelar/{id}")
    public ResponseEntity<?> cancelarCita(@PathVariable Integer id){
        citaMedicaService.cancelarCitaMedica(id);
        return ResponseEntity.ok(new ResponseApi("La cita médica ha sido cancelada",200));
    }
    @PatchMapping("finalizar/{id}")
    public ResponseEntity<?> finalizarCita(@PathVariable Integer id){
        citaMedicaService.finalizarCitaMedica(id);
        return ResponseEntity.ok(new ResponseApi("Se ha finalizado la cita médica",200));
    }
}
