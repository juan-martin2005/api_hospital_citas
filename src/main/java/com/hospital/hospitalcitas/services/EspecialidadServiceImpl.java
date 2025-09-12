package com.hospital.hospitalcitas.services;

import com.hospital.hospitalcitas.dtos.request.EspecialidadRequest;
import com.hospital.hospitalcitas.dtos.response.EspecialidadResponse;
import com.hospital.hospitalcitas.erros.HandlerExistException;
import com.hospital.hospitalcitas.models.Especialidad;
import com.hospital.hospitalcitas.repositories.IEspecialidadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EspecialidadServiceImpl implements IEspecialidadService{
    private final IEspecialidadRepository especialidadRepository;

    @Override
    public List<EspecialidadResponse> findAll() {
        List<EspecialidadResponse> especialidades = especialidadRepository.findAll().stream().map(EspecialidadResponse::new).toList();
        if(especialidades.isEmpty()) {
            throw new NullPointerException();
        }
        return especialidades;
    }

    @Override
    public void save(EspecialidadRequest especialidad) {
        if(especialidadRepository.existsByNombre(especialidad.getNombre())){
           throw new HandlerExistException("La especialidad ya existe");
        };
        Especialidad especialidadDb = new Especialidad();
        especialidadDb.setNombre(especialidad.getNombre());
        especialidadDb.setDescripcion(especialidad.getDescripcion());
        especialidadRepository.save(especialidadDb);
    }

    @Override
    public void update(int id, EspecialidadRequest especialidad) {
        Optional<Especialidad> opEspecialidad = especialidadRepository.findById(id);
        Especialidad especialidadDb = opEspecialidad.orElseThrow(()-> new HandlerExistException("Especialidad no encontrado"));
        especialidadDb.setNombre(especialidad.getNombre());
        especialidadDb.setDescripcion(especialidad.getDescripcion());
        especialidadRepository.save(especialidadDb);
    }

    @Override
    public void delete(int id) {
        Especialidad especialidad = especialidadRepository.findById(id).orElseThrow(()-> new HandlerExistException("Especialidad no encontrado"));
        especialidadRepository.delete(especialidad);
    }
}
