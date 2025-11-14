package com.hospital.hospitalcitas.services.impls;

import com.hospital.hospitalcitas.dtos.response.PacienteResponse;
import com.hospital.hospitalcitas.models.CitaMedica;
import com.hospital.hospitalcitas.repositories.ICitamedicaRepository;
import com.hospital.hospitalcitas.repositories.IDoctorRepository;
import com.hospital.hospitalcitas.repositories.IEspecialidadRepository;
import com.hospital.hospitalcitas.repositories.IPacienteRepository;
import com.hospital.hospitalcitas.repositories.IReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final IPacienteRepository pacienteRepository;
    private final IDoctorRepository doctorRepository;
    private final ICitamedicaRepository citamedicaRepository;
    private final IEspecialidadRepository especialidadRepository;
    private final IReservaRepository reservaRepository;

    public Map<String, Long> getTotals() {
        Map<String, Long> totals = new HashMap<>();
        totals.put("pacientes", pacienteRepository.count());
        totals.put("doctores", doctorRepository.count());
        totals.put("citas", citamedicaRepository.count());
        totals.put("especialidades", especialidadRepository.count());
        totals.put("reservas", reservaRepository.count());
        return totals;
    }

    public List<PacienteResponse> getLatestPacientes(int limit) {
        return pacienteRepository.findAll().stream()
                .sorted(Comparator.comparing(p -> p.getUsuario().getFechaRegistro(), Comparator.nullsLast(Comparator.reverseOrder())))
                .limit(limit)
                .map(PacienteResponse::new)
                .collect(Collectors.toList());
    }


    public Map<String, Long> getCitasByEstado() {
        List<CitaMedica> citas = citamedicaRepository.findAll();
        return citas.stream()
                .collect(Collectors.groupingBy(c -> c.getEstado().name(), Collectors.counting()));
    }

    public Map<String, Long> getCitasByEspecialidad() {
        List<CitaMedica> citas = citamedicaRepository.findAll();
        return citas.stream()
                .collect(Collectors.groupingBy(c -> c.getDoctor().getEspecialidad().getNombre(), Collectors.counting()));
    }
}

