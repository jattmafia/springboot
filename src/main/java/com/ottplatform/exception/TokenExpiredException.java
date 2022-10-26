package com.ottplatform.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.AuthenticationException;

import com.ottplatform.dto.ErrorDTO;

import java.util.List;

public class TokenExpiredException extends AuthenticationException {

    private static final long serialVersionUID = -182410468089342861L;

    List<ErrorDTO> errors;

    public TokenExpiredException(String message) {
        super(message);
    }

    public List<ErrorDTO> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorDTO> errors) {
        this.errors = errors;
    }
}