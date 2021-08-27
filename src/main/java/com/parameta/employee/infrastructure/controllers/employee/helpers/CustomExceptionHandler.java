package com.parameta.employee.infrastructure.controllers.employee.helpers;

import com.parameta.employee.domain.model.domain.common.ex.BusinessException;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.core.codec.DecodingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

@Log
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleBusinessException(BusinessException ex) {
        ex.printStackTrace();

        var errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimestamp(LocalDateTime.now());

        return errorResponse;
    }

    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleDtoValidationException(WebExchangeBindException ex) {
        if (ex.getBindingResult().getFieldError().getField().equals("channel")) {
            log.severe( String.format("El canal %s no está soportado", ex.getBindingResult().getFieldError().getRejectedValue()));
        }
        ex.printStackTrace();

        var errorResponse = new ErrorResponse();
        errorResponse.setMessage("La informacion enviada no genero resultados. Por favor intenta de nuevo.");
        errorResponse.setTimestamp(LocalDateTime.now());

        return errorResponse;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handlePathVariableValidationException(ConstraintViolationException ex) {
        ex.printStackTrace();

        var errorResponse = new ErrorResponse();
        errorResponse.setMessage("La informacion enviada no pudo ser procesada. Por favor intenta de nuevo.");
        errorResponse.setTimestamp(LocalDateTime.now());

        return errorResponse;
    }

    @ExceptionHandler(DecodingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handlePathVariableValidationException(DecodingException ex) {
        ex.printStackTrace();

        var errorResponse = new ErrorResponse();
        errorResponse.setMessage("La informacion enviada no pudo ser procesada. Por favor intenta de nuevo.");
        errorResponse.setTimestamp(LocalDateTime.now());

        return errorResponse;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleAllOtherException(Exception ex) {
        ex.printStackTrace();

        var errorResponse = new ErrorResponse();
        errorResponse.setMessage("No fue posible procesar tu solicitud, por favor intenta de nuevo.");
        errorResponse.setTimestamp(LocalDateTime.now());

        return errorResponse;
    }

    @Data
    private static class ErrorResponse {
        private String message;
        private LocalDateTime timestamp;
    }
}
