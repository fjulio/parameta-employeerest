package com.parameta.employee.domain.model.domain.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtils {

    private static final SimpleDateFormat BIRTHDAY_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private static final String FORMAT_DATE = "dd/MM/yyyy";

    public static boolean isValidDateFormat(Date birthDate) {
        DateFormat sdf = new SimpleDateFormat(FORMAT_DATE);
        sdf.setLenient(false);
        try{
            sdf.parse(String.valueOf(birthDate.getTime()));
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    public static String format(LocalDateTime date) {
        return date.withNano(0).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public static int calculateAge(Date date) {
        var now = LocalDate.now();
        return Period.between(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), now).getYears();
    }

    public static Period calculateDate(Date date) {
        var now = LocalDate.now();
        return Period.between(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), now);
    }
}
