package com.hospital.hospitalcitas.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "doctores")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Doctor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Usuario usuario;
    private String email;
    private String telefono;
    private Character sexo;
    @ManyToOne
    private Especialidad especialidad;
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    List<HorarioDoctor> horarios;
}
