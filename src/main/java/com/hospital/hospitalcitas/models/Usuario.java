package com.hospital.hospitalcitas.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;
    @Column(unique = true)
    private String username;
    private String password;
    private String nombre;
    private String apellido;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuario_roles",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> rol;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    private LocalDate fechaRegistro;
    @PrePersist
    public void prePersist() {
        this.estado = Estado.ACTIVO;
        this.fechaRegistro = LocalDate.now();
    }


}
