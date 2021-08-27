package com.parameta.employee.domain.model.domain.common.ex;

import java.util.function.Supplier;

public class BusinessException extends ApplicationException {

    public enum Type {
        INVALID_FORMAT_DATE("Formato de fecha invalido"),
        INVALID_EMPLOYEE_AGE("Empleado no es mayor de edad"),
        EMPTY_ATTRIBUTE("El atributo no puede ser nulo")
        ;

        private final String message;

        public String getMessage() {
            return message;
        }

        public BusinessException build() {
            return new BusinessException(this);
        }

        public Supplier<Throwable> defer() {
            return () -> new BusinessException(this);
        }

        Type(String message) {
            this.message = message;
        }

    }

    private final Type type;

    public BusinessException(Type type){
        super(type.message);
        this.type = type;
    }

    @Override
    public String getCode(){
        return type.name();
    }


}
