package com.hospital.hospitalcitas.dtos.response;

public record ReservaResponse(
        Integer id,
        String receiptNumber,
        Long paymentId,
        String paymentStatus
) {}

