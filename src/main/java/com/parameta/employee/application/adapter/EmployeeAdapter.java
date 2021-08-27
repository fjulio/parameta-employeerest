package com.parameta.employee.application.adapter;

import com.parameta.employee.domain.model.domain.employee.Employee;
import com.parameta.employee.domain.model.domain.employee.gateway.EmployeeRepository;
import com.parameta.employee.infrastructure.repositories.impl.EmployeeRepositorySoapImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class EmployeeAdapter implements EmployeeRepository {

    @Autowired
    EmployeeRepositorySoapImpl employeeRepositorySoap;

    @Override
    public Mono<Employee> save(Employee employee) {
        return employeeRepositorySoap.saveEmployeeSoap(employee);
    }
}
