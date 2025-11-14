package com.hospital.hospitalcitas.services.impls;

import com.hospital.hospitalcitas.dtos.response.ReservaResponse;
import com.hospital.hospitalcitas.erros.HandlerExistException;
import com.hospital.hospitalcitas.metodoPago.dto.PaymentResponseDto;
import com.hospital.hospitalcitas.models.*;
import com.hospital.hospitalcitas.repositories.ICitamedicaRepository;
import com.hospital.hospitalcitas.repositories.IHistoriaClinicaRepository;
import com.hospital.hospitalcitas.repositories.IReservaRepository;
import com.hospital.hospitalcitas.repositories.IPacienteRepository;
import com.hospital.hospitalcitas.services.interfaces.IReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservaServiceImpl implements IReservaService {

    private final IReservaRepository reservaRepository;
    private final ICitamedicaRepository citamedicaRepository;
    private final IPacienteRepository pacienteRepository;
    private final IHistoriaClinicaRepository historiaClinicaRepository;

    @Override
    public ReservaResponse createReservaFromPayment(Integer citaId, PaymentResponseDto paymentResponse) {
        CitaMedica cita = citamedicaRepository.findById(citaId).orElseThrow(() -> new HandlerExistException("Cita no encontrada"));
        Paciente paciente = cita.getPaciente();

        Reserva reserva = Reserva.builder()
                .cita(cita)
                .paciente(paciente)
                .paymentId(paymentResponse.id())
                .paymentStatus(paymentResponse.status())
                .amount(paymentResponse.transactionAmount())
                .receiptNumber("RCPT-" + paymentResponse.id())
                .build();

        reserva = reservaRepository.save(reserva);

        // Crear un registro inicial en la historia clínica (vacío)
        HistoriaClinica historia = HistoriaClinica.builder()
                .cita(cita)
                .paciente(paciente)
                .diagnostico("")
                .observaciones("Reserva registrada, pendiente atención")
                .build();
        historiaClinicaRepository.save(historia);

        return new ReservaResponse(reserva.getId(), reserva.getReceiptNumber(), reserva.getPaymentId(), reserva.getPaymentStatus());
    }

    @Override
    public List<ReservaResponse> findReservasByPaciente(Integer pacienteId) {
        List<Reserva> reservas = reservaRepository.findByPaciente_Id(pacienteId);
        return reservas.stream()
                .map(r -> new ReservaResponse(r.getId(), r.getReceiptNumber(), r.getPaymentId(), r.getPaymentStatus()))
                .toList();
    }

    @Override
    public ReservaResponse findReservaByCita(Integer citaId) {
        Optional<Reserva> reservaOpt = reservaRepository.findByCita_Id(citaId);
        Reserva reserva = reservaOpt.orElseThrow(() -> new HandlerExistException("Reserva no encontrada para la cita"));
        return new ReservaResponse(reserva.getId(), reserva.getReceiptNumber(), reserva.getPaymentId(), reserva.getPaymentStatus());
    }
}
