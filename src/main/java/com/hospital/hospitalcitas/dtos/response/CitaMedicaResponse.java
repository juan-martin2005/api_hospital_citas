package com.hospital.hospitalcitas.dtos.response;

import com.hospital.hospitalcitas.models.CitaMedica;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CitaMedicaResponse {

    private int id;
    private String nombreDoctor;
    private String especialidadDoctor;
    private HorarioDoctorResponse horarioDoctor;

    public CitaMedicaResponse(CitaMedica citaMedica) {
        this.id = citaMedica.getId();
        this.nombreDoctor = citaMedica.getDoctor().getUsuario().getNombre();
        this.especialidadDoctor = citaMedica.getDoctor().getEspecialidad().getNombre();
        this.horarioDoctor = new HorarioDoctorResponse(citaMedica.getHorario());
    }
}
