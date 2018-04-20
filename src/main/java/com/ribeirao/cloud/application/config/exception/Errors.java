package com.ribeirao.cloud.application.config.exception;

public class Errors {
    private Object[] errors;

    public Errors(Object[] errors) {
        this.errors = errors;
    }

    public Object[] getErrors() {
        return errors;
    }

    public void setErrors(Object[] errors) {
        this.errors = errors;
    }
}
