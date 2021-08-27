package com.parameta.employee.infrastructure.repositories.impl;

import com.parameta.employee.domain.model.domain.employee.Employee;
import com.parameta.employee.infrastructure.web_services.EmployeesPort;
import com.parameta.employee.infrastructure.web_services.EmployeesPortService;
import com.parameta.employee.infrastructure.web_services.SaveEmployeeRequest;
import com.parameta.employee.infrastructure.web_services.SaveEmployeeResponse;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.util.Date;
import java.util.GregorianCalendar;

@Repository
public class EmployeeRepositorySoapImpl {

    public Mono<Employee> saveEmployeeSoap(Employee employee){
        EmployeesPort employeesPort = new EmployeesPortService().getEmployeesPortSoap11();
        SaveEmployeeRequest request = new SaveEmployeeRequest();
        request.setEmployee(convertEmployeeToEmployeeSoap(employee));
        SaveEmployeeResponse response = employeesPort.saveEmployee(request);
        return Mono.just(convertEmployeeSoapToEmployee(response.getEmployee()));
    }

    private com.parameta.employee.infrastructure.web_services.Employee convertEmployeeToEmployeeSoap(Employee employee){
        com.parameta.employee.infrastructure.web_services.Employee emp  = new com.parameta.employee.infrastructure.web_services.Employee();
        emp.setNumeroDocumento(employee.getNumeroDocumento());
        emp.setTipoDocumento(employee.getTipoDocumeto());
        emp.setNombre(employee.getNombre());
        emp.setApellido(employee.getApellido());
        emp.setFechaNacimiento(toXMLGregorianCalendar(employee.getFechaNacimiento()));
        emp.setFechaVinculacion(toXMLGregorianCalendar(employee.getFechaVinculacion()));
        emp.setCargo(employee.getCargo());
        emp.setSalario(employee.getSalario());
        return emp;
    }

    private Employee convertEmployeeSoapToEmployee(com.parameta.employee.infrastructure.web_services.Employee employee){
        return Employee.builder()
                .numeroDocumento(employee.getNumeroDocumento())
                .tipoDocumeto(employee.getTipoDocumento())
                .nombre(employee.getNombre())
                .apellido(employee.getApellido())
                .fechaNacimiento(employee.getFechaNacimiento().toGregorianCalendar().getTime())
                .fechaVinculacion(employee.getFechaVinculacion().toGregorianCalendar().getTime())
                .cargo(employee.getCargo())
                .salario(employee.getSalario())
                .build();
    }

    public static XMLGregorianCalendar toXMLGregorianCalendar(Date date)
    {
        GregorianCalendar gCalendar
                = new GregorianCalendar();
        gCalendar.setTime(date);
        XMLGregorianCalendar xmlCalendar = null;
        try {
            xmlCalendar
                    = DatatypeFactory.newInstance()
                    .newXMLGregorianCalendar(gCalendar);
        }
        catch (DatatypeConfigurationException ex) {
            System.out.println(ex);
        }
        return xmlCalendar;
    }

    public static LocalDate toLocalDate(XMLGregorianCalendar xmlGregorianCalendar){
        LocalDate localDate = LocalDate.of(
                xmlGregorianCalendar.getYear(),
                xmlGregorianCalendar.getMonth(),
                xmlGregorianCalendar.getDay());
        return localDate;
    }
}
