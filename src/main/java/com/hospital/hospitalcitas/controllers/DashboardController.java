package com.hospital.hospitalcitas.controllers;

import com.hospital.hospitalcitas.dtos.response.CitaMedicaResponse;
import com.hospital.hospitalcitas.dtos.response.PacienteResponse;
import com.hospital.hospitalcitas.services.impls.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/totales")
    public ResponseEntity<?> getTotals() {
        return ResponseEntity.ok(dashboardService.getTotals());
    }

    @GetMapping("/ultimos-pacientes")
    public ResponseEntity<?> getLatestPacientes(@RequestParam(defaultValue = "5") int limit) {
        List<PacienteResponse> data = dashboardService.getLatestPacientes(limit);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/citas-por-estado")
    public ResponseEntity<?> getCitasByEstado() {
        Map<String, Long> data = dashboardService.getCitasByEstado();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/citas-por-especialidad")
    public ResponseEntity<?> getCitasByEspecialidad() {
        Map<String, Long> data = dashboardService.getCitasByEspecialidad();
        return ResponseEntity.ok(data);
    }
}

