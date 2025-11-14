package com.hospital.hospitalcitas.metodoPago;

import com.hospital.hospitalcitas.erros.HandlerMercadoPagoException;
import com.hospital.hospitalcitas.metodoPago.dto.PaymentDto;
import com.hospital.hospitalcitas.metodoPago.dto.PaymentResponseDto;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.core.MPRequestOptions;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class MercadoPagoService {
    @Value("${ACCESS_TOKEN}")
    private String token;
    private final String uniqueValue = UUID.randomUUID().toString();

    public Map<String, String> getMercadoPagoWallet() {

        MercadoPagoConfig.setAccessToken(token);
        // Crear un objeto de preferencia
        PreferenceClient client = new PreferenceClient();

        // Crear un elemento en la preferencia
        List<PreferenceItemRequest> items = new ArrayList<>();
        PreferenceItemRequest item =
                PreferenceItemRequest.builder()
                        .title("Cita médica")
                        .quantity(1)
                        .unitPrice(new BigDecimal("100"))
                        .build();
        items.add(item);

        PreferenceRequest request = PreferenceRequest.builder()
                .items(items).build();
        try {
            var response = client.create(request);
            Map<String,String> responseId = new HashMap<String,String>();
            responseId.put("id",response.getId());
            responseId.put("initPoint",response.getInitPoint());
            return  responseId;
        } catch (MPException | MPApiException e) {
            throw new RuntimeException(e);
        }
    }

    public PaymentResponseDto getPaymentClient(PaymentDto request)  {

        try {
            Map<String, String> customHeader = getMercadoPagoWallet();
            customHeader.put("x-idempotency-key", uniqueValue);

            MPRequestOptions requestOptions = MPRequestOptions.builder()
                    .customHeaders(customHeader)
                    .build();

            MercadoPagoConfig.setAccessToken(token);

            PaymentClient client = new PaymentClient();

            PaymentCreateRequest paymentCreateRequest = PaymentCreateRequest.builder()
                    .installments(request.installments())
                    .issuerId(request.issuer_Id())
                    .payer(PaymentPayerRequest.builder()
                            .email(request.payer().email())
                            .identification(IdentificationRequest.builder()
                                    .type(request.payer().identification().type())
                                    .number(request.payer().identification().number())
                                    .build())
                            .build())
                    .description("Cita médica ")
                    .paymentMethodId(request.payment_method_id())
                    .token(request.token())
                    .transactionAmount(request.transaction_amount())
                    .build();

            Payment payment = client.create(paymentCreateRequest, requestOptions);

           return new PaymentResponseDto(payment.getId(),  payment.getStatus(), payment.getDescription(), payment.getTransactionAmount());
        } catch (MPException e) {
            System.out.println("ERROR CON MERCADO PAGO" +e.getMessage());
            throw new HandlerMercadoPagoException("ERROR CON MERCADO PAGO" +e.getMessage());
        } catch (MPApiException e) {
            System.out.println(e.getApiResponse().getStatusCode());
            System.out.println("ERROR CON LA API DE MERCADO PAGO" +e.getApiResponse().getContent());
            throw new HandlerMercadoPagoException("ERROR CON LA API DE MERCADO PAGO" +e.getApiResponse().getContent());
        }

    }
}
