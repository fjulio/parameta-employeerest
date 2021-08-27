package com.parameta.employee.domain.model.domain.employee.gateway;

import com.parameta.employee.domain.model.domain.employee.Employee;
import reactor.core.publisher.Mono;

public interface EmployeeRepository {

    Mono<Employee> save(Employee employee);
}
