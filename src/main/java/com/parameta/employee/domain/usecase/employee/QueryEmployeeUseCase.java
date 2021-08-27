package com.parameta.employee.domain.usecase.employee;

import com.parameta.employee.domain.model.domain.employee.Employee;
import com.parameta.employee.domain.model.domain.employee.EmployeeFactory;
import com.parameta.employee.domain.model.domain.employee.gateway.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Date;

@RequiredArgsConstructor
public class QueryEmployeeUseCase {

    private  final EmployeeRepository employeeRepository;

    public Mono<Employee> createEmployee(String numeroDocumento, String tipoDocumento, String nombre, String apellido, Date fechaNacimiento, Date fechaVinculacion, String cargo, double salario){
        return EmployeeFactory.createEmployee(numeroDocumento, tipoDocumento, nombre,apellido,fechaNacimiento, fechaVinculacion, cargo, salario)
                .flatMap(employee -> employeeRepository.save(employee))
                .onErrorResume(e -> Mono.empty())
                .switchIfEmpty(Mono.defer(()-> Mono.just(EmployeeFactory.createEmployeeDefault())));

    }

}
