package com.hospital.hospitalcitas.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@Entity
@Table(name = "citas_medicas")
@AllArgsConstructor
@NoArgsConstructor
public class CitaMedica {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private HorarioDoctor horario;
    @ManyToOne
    private Paciente paciente;
    @ManyToOne
    private Doctor doctor;
    @Enumerated(EnumType.STRING)
    private Estado estado;

}
