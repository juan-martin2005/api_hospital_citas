package com.hospital.hospitalcitas.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false)
    private String dni;
    private String email;
    private String telefono;
    private Character sexo;
    private LocalDate fechaNacimiento;
    @ManyToOne
    private Usuario usuario;

    @PrePersist
    public void prePersist() {
        this.sexo = Character.toUpperCase(this.sexo);
    }
}
