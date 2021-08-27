package com.parameta.employee.domain.model.domain.employee;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode
@ToString
public class Employee {

    private String numeroDocumento;

    private String tipoDocumeto;

    private String nombre;

    private String apellido;

    private Date fechaNacimiento;

    private Date fechaVinculacion;

    private String cargo;

    private double salario;
}
