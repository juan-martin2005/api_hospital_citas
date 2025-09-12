package com.hospital.hospitalcitas.erros;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class HandlerErrorExceptionController {

//    @ExceptionHandler({NoHandlerFoundException.class})
//    public ResponseEntity<?> handleValidationErrors(NoHandlerFoundException ex) {
//        ErrorPojo error = new ErrorPojo();
//        error.setError(ex.getMessage());
//        error.setStatus(HttpStatus.NOT_FOUND.value());
//        error.setMessage("La petición al recurso no ha sido encontrado");
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
//    }
    @ExceptionHandler({NoResourceFoundException.class})
    public ResponseEntity<?> handleValidationErrors(NoResourceFoundException ex) {
        ErrorMessage error = new ErrorMessage();
        error.setError(ex.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage("La petición al recurso "+ex.getResourcePath() +" no ha sido encontrado");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity<?> handleValidationErrors(NullPointerException ex) {
        ErrorMessage error = new ErrorMessage();
        error.setError(ex.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage("La lista se encuentra vacía");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {
        ErrorMessage error = new ErrorMessage();
        Map<String, Object> campoVacio = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(
                e -> campoVacio.put(e.getField(),e.getDefaultMessage())
        );
        error.setError("Campos vacios");
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(campoVacio);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    @ExceptionHandler({HandlerExistException.class})
    public ResponseEntity<?> handleValidationErrors(HandlerExistException ex) {
        ErrorMessage error = new ErrorMessage();
        error.setError("Error con la existencia");
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.CONFLICT.value());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
    @ExceptionHandler({HandlerDateTimeException.class})
    public ResponseEntity<?> handleValidationErrors(HandlerDateTimeException ex) {
        ErrorMessage error = new ErrorMessage();
        error.setError("Error con la fecha u hora");
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.CONFLICT.value());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<?> handleValidationErrors(DataIntegrityViolationException ex) {
        ErrorMessage error = new ErrorMessage();
        error.setError("No puede haber entradas duplicadas");
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage("Error interno con la base de datos");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<?> handleValidationErrors(BadCredentialsException ex) {
        ErrorMessage error = new ErrorMessage();
        error.setError("Credenciales incorrectas");
        error.setStatus(HttpStatus.UNAUTHORIZED.value());
        error.setMessage("Nombre de usuario o contraseña incorrecto");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }
    @ExceptionHandler({HandlerCitaException.class})
    public ResponseEntity<?> handleValidationErrors(HandlerCitaException ex) {
        ErrorMessage error = new ErrorMessage();
        error.setError("Error con la cita médica");
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


}
