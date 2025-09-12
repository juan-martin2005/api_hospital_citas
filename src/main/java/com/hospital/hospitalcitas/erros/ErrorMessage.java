package com.hospital.hospitalcitas.erros;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorMessage {
    private String error;
    private Object message;
    private int status;
}
