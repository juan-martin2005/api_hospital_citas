package com.hospital.hospitalcitas.erros;

public class HandlerExistException extends RuntimeException {
    public HandlerExistException(String message) {
        super(message);
    }
}
