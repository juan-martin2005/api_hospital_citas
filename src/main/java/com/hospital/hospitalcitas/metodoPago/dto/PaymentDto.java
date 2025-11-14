package com.hospital.hospitalcitas.metodoPago.dto;

import java.math.BigDecimal;

public record PaymentDto(
        Integer installments,
        String issuer_Id,
        PayerDto payer,
        String token,
        String payment_method_id,
        BigDecimal transaction_amount

) {
}
