package com.parameta.employee.infrastructure.controllers.employee;

import com.parameta.employee.domain.model.domain.employee.EmployeeFactory;
import com.parameta.employee.domain.usecase.employee.QueryEmployeeUseCase;
import com.parameta.employee.infrastructure.controllers.employee.dto.EmployeeDto;
import com.parameta.employee.infrastructure.controllers.employee.helpers.EntityModelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

import static reactor.core.publisher.Mono.defer;
import static reactor.core.publisher.Mono.just;

@RestController
@RequestMapping("/api")
public class EmployeeContoller {

    private QueryEmployeeUseCase employeeUseCase;

    @Autowired
    public EmployeeContoller(QueryEmployeeUseCase employeeUseCase){
        this.employeeUseCase = employeeUseCase;
    }

    @GetMapping(value = "/employee", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Object>> createNew(@Valid EmployeeDto data){
        return employeeUseCase.createEmployee(data.getNumeroDocumento(), data.getTipoDocumento(), data.getNombre(), data.getApellido(), data.getFechaNacimiento(),data.getFechaVinculacion(), data.getCargo(), data.getSalario())
                .flatMap(employee -> EntityModelHelper.createResponse(employee))
                .switchIfEmpty(defer(() -> just(ResponseEntity.status(HttpStatus.OK).body("Error"))));
    }
}
