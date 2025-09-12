package com.hospital.hospitalcitas.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class HorarioDoctor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    private LocalDate fecha;
    @Column(columnDefinition = "TIME(0)")
    private LocalTime horaInicio;
    @Column(columnDefinition = "TIME(0)")
    private LocalTime horaFin;
    @ManyToOne
    private Doctor doctor;

    @PrePersist
    public void prePersist() {
        this.estado = Estado.LIBRE;
    }
}
