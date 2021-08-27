package com.parameta.employee.domain.model.domain.employee;

import static com.parameta.employee.domain.model.domain.common.ex.BusinessException.Type.*;
import static com.parameta.employee.domain.model.domain.common.DateUtils.*;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static reactor.core.publisher.Mono.error;
import static reactor.core.publisher.Mono.just;

public class EmployeeFactory {

    public static Mono<Employee> createEmployee(String numeroDocumento, String tipoDocumento, String nombre, String apellido, Date fechaNacimiento, Date fechaVinculacion, String cargo, double salario){

        if(numeroDocumento == null || "".equals(numeroDocumento)){
            return  error(EMPTY_ATTRIBUTE.build());
        }

        if(tipoDocumento == null || "".equals(tipoDocumento)){
            return  error(EMPTY_ATTRIBUTE.build());
        }

        if(nombre == null || "".equals(nombre)){
            return  error(EMPTY_ATTRIBUTE.build());
        }

        if(apellido == null || "".equals(apellido)){
            return  error(EMPTY_ATTRIBUTE.build());
        }

        if(fechaNacimiento == null || fechaNacimiento.getTime() == 0 ){
            return  error(EMPTY_ATTRIBUTE.build());
        }

        if(fechaVinculacion == null || fechaVinculacion.getTime() == 0 ){
            return  error(EMPTY_ATTRIBUTE.build());
        }

        if(cargo == null || "".equals(cargo)){
            return  error(EMPTY_ATTRIBUTE.build());
        }

        if(salario == 0.0){
            return  error(EMPTY_ATTRIBUTE.build());
        }

        if(calculateAge(fechaNacimiento) < 18){
            return  error(INVALID_EMPLOYEE_AGE.build());
        }

        return  just(Employee.builder().numeroDocumento(numeroDocumento).tipoDocumeto(tipoDocumento).nombre(nombre).apellido(apellido).fechaNacimiento(fechaNacimiento).fechaVinculacion(fechaVinculacion).cargo(cargo).salario(salario).build());
    }

    public static Employee createEmployeeDefault() {

        try {
            return  Employee.builder()
                    .numeroDocumento("1101453184")
                    .tipoDocumeto("CC")
                    .nombre("Fabio Andres")
                    .apellido("Julio Pina")
                    .fechaNacimiento(new SimpleDateFormat("dd/MM/yyyy").parse("22/11/1991"))
                    .fechaVinculacion(new SimpleDateFormat("dd/MM/yyyy").parse("04/06/2019"))
                    .cargo("Architect Developer")
                    .salario(6000000.00)
                    .build();
        }catch (Exception ex){
            return  Employee.builder().build();
        }

    }
}
