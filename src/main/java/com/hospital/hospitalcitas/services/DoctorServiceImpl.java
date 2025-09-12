package com.hospital.hospitalcitas.services;

import com.hospital.hospitalcitas.dtos.request.DoctorRequest;
import com.hospital.hospitalcitas.dtos.response.DoctorResponse;
import com.hospital.hospitalcitas.erros.HandlerExistException;
import com.hospital.hospitalcitas.models.*;
import com.hospital.hospitalcitas.repositories.IDoctorRepository;
import com.hospital.hospitalcitas.repositories.IEspecialidadRepository;
import com.hospital.hospitalcitas.repositories.IRoleRepository;
import com.hospital.hospitalcitas.repositories.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements IDoctorService{
    private final IDoctorRepository doctorRepository;
    private final IRoleRepository roleRepository;
    private final IUsuarioRepository usuarioRepository;
    private final IEspecialidadRepository especialidadRepository;
    private final PasswordEncoder  passwordEncoder;

    @Override
    public List<DoctorResponse> findAll() {
        return doctorRepository.findAll().stream().map(DoctorResponse::new).toList();
    }

    @Override
    public List<DoctorResponse> findByEspecialidad(String especialidad) {
        List<DoctorResponse> doctors = doctorRepository.findByEspecialidad_Nombre(especialidad).stream().map(DoctorResponse::new).toList();
        if(doctors.isEmpty()) {
           throw new HandlerExistException("No se ha encontrado doctores con esa especialidad");
        }
        return doctors;
    }

    @Override
    public void save(DoctorRequest doctor) {
        Optional<Role> optRole = roleRepository.findByNombre("ROLE_DOCTOR");
        Set<Role> role = new HashSet<>();
        optRole.ifPresent(role::add);
        Especialidad especialidad = especialidadRepository.findByNombre(doctor.getEspecialidad()).
                orElseThrow(() -> new HandlerExistException("La especialidad no existe"));
        if(usuarioRepository.existsByUsername(doctor.getEmail())){
            throw new HandlerExistException("El nombre de usuario ya existe");
        }
        String password = passwordEncoder.encode(doctor.getPassword());
        Usuario uDoctor = Usuario.builder()
                .nombre(doctor.getNombre())
                .apellido(doctor.getApellido())
                .username(doctor.getEmail())
                .password(password)
                .rol(role)
                .build();
        Doctor doctorDb = Doctor.builder()
                .usuario(uDoctor)
                .email(doctor.getEmail())
                .telefono(doctor.getTelefono())
                .sexo(doctor.getSexo().charAt(0))
                .especialidad(especialidad)
                .build();
        usuarioRepository.save(uDoctor);
        doctorRepository.save(doctorDb);
    }

    @Override
    public void update(int id, DoctorRequest doctor) {
        Optional<Doctor> oDoctor = doctorRepository.findById(id);
        Doctor doctorDb = oDoctor.orElseThrow(()-> new HandlerExistException("El doctor no existe"));
        doctorDb.getUsuario().setPassword(doctor.getPassword());
        doctorRepository.save(doctorDb);
    }
}
