package com.hospital.hospitalcitas.dtos.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CitaMedicaRequest {

    private Integer idDoctor;
    private Integer idHorario;

}
