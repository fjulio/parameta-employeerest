package com.parameta.employee.infrastructure.configuration;

import com.parameta.employee.domain.model.domain.employee.gateway.EmployeeRepository;
import com.parameta.employee.domain.usecase.employee.QueryEmployeeUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public QueryEmployeeUseCase employeeUseCase(EmployeeRepository employeeRepository){
        return new QueryEmployeeUseCase(employeeRepository);
    }
}
