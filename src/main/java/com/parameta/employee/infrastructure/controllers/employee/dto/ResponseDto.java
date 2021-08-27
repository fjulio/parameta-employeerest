package com.parameta.employee.infrastructure.controllers.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto {
    private String tiempoVinculacion;
    private String edadActual;
}
