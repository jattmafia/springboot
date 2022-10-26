package com.ottplatform.exception;

import java.util.List;

import org.springframework.security.core.AuthenticationException;

import com.ottplatform.dto.ErrorDTO;

public class InvalidRequestException extends AuthenticationException {

    /**
     *
     */

    private static final long serialVersionUID = -7716499252371060596L;

    List<ErrorDTO> errors;

    public InvalidRequestException(String message) {
        super(message);
    }

    public List<ErrorDTO> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorDTO> errors) {
        this.errors = errors;
    }
}