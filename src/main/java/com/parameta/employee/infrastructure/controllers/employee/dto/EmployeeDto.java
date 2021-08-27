package com.parameta.employee.infrastructure.controllers.employee.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class EmployeeDto {

    @Pattern(regexp = "^[a-zA-Z0-9]{3,16}$")
    private String numeroDocumento;
    @Pattern(regexp = "^[A-Z]{2}$")
    private String tipoDocumento;
    @Pattern(regexp = "^[a-zA-Z0-9]{3,100}$")
    private String nombre;
    @Pattern(regexp = "[a-zA-Z0-9]{3,100}$")
    private String apellido;
    @JsonFormat(pattern="dd/MM/yyyy", locale="es_CO", timezone="America/Bogota")
    @Past
    private Date fechaNacimiento;
    @JsonFormat(pattern="dd/MM/yyyy", locale="es_CO", timezone="America/Bogota")
    private Date fechaVinculacion;
    @Pattern(regexp = "^[a-zA-Z0-9]{3,100}$")
    private String cargo;

    private double salario;

}
