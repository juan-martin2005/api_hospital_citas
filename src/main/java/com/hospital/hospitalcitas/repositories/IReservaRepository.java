package com.hospital.hospitalcitas.repositories;

import com.hospital.hospitalcitas.models.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface IReservaRepository extends JpaRepository<Reserva, Integer> {
    Optional<Reserva> findByReceiptNumber(String receiptNumber);
    List<Reserva> findByPaciente_Id(Integer pacienteId);
    Optional<Reserva> findByCita_Id(Integer citaId);
}

