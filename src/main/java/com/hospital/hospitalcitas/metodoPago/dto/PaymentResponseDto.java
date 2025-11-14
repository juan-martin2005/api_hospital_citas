package com.hospital.hospitalcitas.metodoPago.dto;

import java.math.BigDecimal;

public record PaymentResponseDto(
    Long id,
    String status,
    String description,
    BigDecimal transactionAmount
) {}
