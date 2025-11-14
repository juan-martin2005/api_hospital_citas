package com.hospital.hospitalcitas.metodoPago;

import com.hospital.hospitalcitas.metodoPago.dto.PaymentDto;
import com.hospital.hospitalcitas.metodoPago.dto.PaymentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("api/mercado-pago")
@RequiredArgsConstructor
public class MercadoPagoController {
    private final MercadoPagoService  mercadoPagoService;
    @PostMapping("/proceso_pago")
    public ResponseEntity<?> procesoPago(){
        return ResponseEntity.ok(mercadoPagoService.getMercadoPagoWallet());
    }

    @PostMapping("/process_payment")
    public ResponseEntity<?> procesoPago(@RequestBody PaymentDto request){
        PaymentResponseDto payment = mercadoPagoService.getPaymentClient(request);
        System.out.println(payment);
        return ResponseEntity.status(HttpStatus.CREATED).body(payment);
    }
}
