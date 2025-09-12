package com.hospital.hospitalcitas.controllers;

import com.hospital.hospitalcitas.dtos.request.HorarioDoctorRequest;
import com.hospital.hospitalcitas.services.IHorarioDoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/horario")
@RequiredArgsConstructor
public class HorarioController {
    private final IHorarioDoctorService horarioDoctorService;

    @GetMapping
    public ResponseEntity<?> listarHorario(){
        return ResponseEntity.ok(horarioDoctorService.findAllByDoctor());
    }

    @PostMapping
    public ResponseEntity<?> crearHorario(@Valid @RequestBody HorarioDoctorRequest horario){
        horarioDoctorService.save(horario);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseApi("El horario se ha creado", HttpStatus.CREATED.value()));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarHorario(@PathVariable int id ,@Valid @RequestBody HorarioDoctorRequest horario){
        horarioDoctorService.update(id, horario);
        return ResponseEntity.ok(new ResponseApi("El horario se ha modificado", HttpStatus.OK.value()));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarHorario(@PathVariable int id){
        horarioDoctorService.delete(id);
        return ResponseEntity.ok(new ResponseApi("El horario se eliminado", HttpStatus.OK.value()));
    }
}
