package com.ribeirao.cloud.application.config.exception;

public class NotFoundException extends Exception {

    public NotFoundException() {
        super("Veículo não encontrado");
    }
}
