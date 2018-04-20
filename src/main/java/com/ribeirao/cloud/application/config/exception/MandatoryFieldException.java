package com.ribeirao.cloud.application.config.exception;

import java.util.List;

public class MandatoryFieldException extends RuntimeException {
    private Errors errors;

    public MandatoryFieldException(List<String> errors) {
        super();
        this.errors = new Errors(errors.toArray());
    }

    public Errors getErrors() {
        return errors;
    }
}
