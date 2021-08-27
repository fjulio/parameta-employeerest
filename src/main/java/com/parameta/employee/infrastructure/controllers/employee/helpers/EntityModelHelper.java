package com.parameta.employee.infrastructure.controllers.employee.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.parameta.employee.domain.model.domain.employee.Employee;
import com.parameta.employee.infrastructure.controllers.employee.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.time.Period;
import java.util.Date;

import static com.parameta.employee.domain.model.domain.common.DateUtils.*;
import static reactor.core.publisher.Mono.just;

public class EntityModelHelper {

    private static String APP_CONTEXT;

    @Value("${app.context}")
    public void setAppContext(String appContext) {
        APP_CONTEXT = appContext;
    }

    public static Mono<ResponseEntity<Object>> createResponse(Employee employee){
        ResponseDto responseDto = new ResponseDto(getBondingTime(employee.getFechaVinculacion()), getActualAge(employee.getFechaNacimiento()));

        ObjectMapper objectMapper = new ObjectMapper();
        String  response= "";
        try {
            response = objectMapper.writeValueAsString(responseDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        ResponseEntity.BodyBuilder builder = ResponseEntity.status(HttpStatus.CREATED);
        return just(builder.body(response));
    }

    private static String getActualAge(Date birthDate){
        Period birthDatePeriod = calculateDate(birthDate);
        return "le edad del empleado es de ".concat(String.valueOf(birthDatePeriod.getYears())).concat(" años, ").concat(String.valueOf(birthDatePeriod.getMonths())).concat(" meses, ").concat(String.valueOf(birthDatePeriod.getDays()).concat(" días."));
    }
    private static String getBondingTime(Date bondingTime){
        Period bondingTimePeriod = calculateDate(bondingTime);
        return "el tiempo de vinculación del empleado es de ".concat(String.valueOf(bondingTimePeriod.getYears())).concat(" años, ").concat(String.valueOf(bondingTimePeriod.getMonths())).concat(" meses, ").concat(String.valueOf(bondingTimePeriod.getDays()).concat(" días."));
    }
}
