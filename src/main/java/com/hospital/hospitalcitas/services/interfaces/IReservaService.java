package com.hospital.hospitalcitas.services.interfaces;

import com.hospital.hospitalcitas.dtos.response.ReservaResponse;
import com.hospital.hospitalcitas.metodoPago.dto.PaymentResponseDto;
import java.util.List;

public interface IReservaService {
    ReservaResponse createReservaFromPayment(Integer citaId, PaymentResponseDto paymentResponse);
    List<ReservaResponse> findReservasByPaciente(Integer pacienteId);
    ReservaResponse findReservaByCita(Integer citaId);
}

