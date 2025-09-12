package com.hospital.hospitalcitas.services;

import ch.qos.logback.classic.encoder.JsonEncoder;
import com.hospital.hospitalcitas.dtos.request.PacienteRequest;
import com.hospital.hospitalcitas.dtos.response.PacienteResponse;
import com.hospital.hospitalcitas.erros.HandlerExistException;
import com.hospital.hospitalcitas.models.*;
import com.hospital.hospitalcitas.repositories.IEspecialidadRepository;
import com.hospital.hospitalcitas.repositories.IPacienteRepository;
import com.hospital.hospitalcitas.repositories.IRoleRepository;
import com.hospital.hospitalcitas.repositories.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PacienteServiceImpl implements IPacienteService{

    private final IPacienteRepository pacienteRepository;
    private final IRoleRepository roleRepository;
    private final IUsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public List<PacienteResponse> findAll() {
        return pacienteRepository.findAll().stream().map(PacienteResponse::new).toList();
    }

    @Override
    public PacienteResponse findById(int id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        return paciente.map(PacienteResponse::new).orElseThrow(() ->new HandlerExistException("El paciendo no se ha encontrado"));
    }

    @Override
    public void save(PacienteRequest paciente) {

        Optional<Role> optRole = roleRepository.findByNombre("ROLE_PACIENTE");
        Set<Role> role = new HashSet<>();
        optRole.ifPresent(role::add);
        if(usuarioRepository.existsByUsername(paciente.getDni())){
            throw new HandlerExistException("El dni del paciente ya existe");
        }
        String password = passwordEncoder.encode(paciente.getPassword());

        //***El paciente se registra con su DNI e inicia sesion con su DNI también***//
        Usuario uPaciente = Usuario.builder()
                .nombre(paciente.getNombre())
                .apellido(paciente.getApellido())
                .username(paciente.getDni())
                .password(password)
                .rol(role)
                .build();
        Paciente pacientDb = Paciente.builder()
                .usuario(uPaciente)
                .dni(paciente.getDni())
                .email(paciente.getEmail())
                .telefono(paciente.getTelefono())
                .fechaNacimiento(paciente.getFechaNacimiento())
                .sexo(paciente.getSexo().charAt(0))
                .build();
        usuarioRepository.save(uPaciente);
        pacienteRepository.save(pacientDb);
    }

    //**El paciente puede actualizar su contraseña o telefono***//
    @Override
    public void update(int id, PacienteRequest paciente) {
        Paciente pacienteDb = pacienteRepository.findById(id).orElseThrow(()-> new HandlerExistException("El paciente no se ha encontrado"));
        pacienteDb.setTelefono(paciente.getTelefono());
        pacienteDb.getUsuario().setPassword(paciente.getPassword());
        pacienteRepository.save(pacienteDb);

    }
}
