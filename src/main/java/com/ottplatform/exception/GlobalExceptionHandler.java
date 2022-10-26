package com.ottplatform.exception;

import com.ottplatform.enums.ErrorCodes;
import com.ottplatform.response.ApiResponse;
import com.ottplatform.util.ResponseBuilder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String INVALID_REQUEST = "Error - Bad Request: ";
    private static final String INTERNAL_SERVER_ERROR = "Error - Internal Server Error: ";
    private static final String ERROR = "Error: ";

    @ExceptionHandler({ TokenExpiredException.class })
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ApiResponse<Void> tokenExpiredException(TokenExpiredException ex) {
        log.error(INVALID_REQUEST + ex);
        ErrorCodes errorCode;
        if (null == ex.getErrors()) {
            errorCode = ErrorCodes.getEnum(ex.getMessage());
            return ResponseBuilder.getErrorResponse(errorCode.getError());
        }
        return ResponseBuilder.getErrorResponse(ex.getErrors());
    }

    @ExceptionHandler({ InvalidRequestException.class })
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ApiResponse<Void> invalidRequestException(InvalidRequestException ex) {
        log.error(INVALID_REQUEST + ex);
        ErrorCodes errorCode;
        if (null == ex.getErrors()) {
            errorCode = ErrorCodes.getEnum(ex.getMessage());
            return ResponseBuilder.getErrorResponse(errorCode.getError());
        }
        return ResponseBuilder.getErrorResponse(ex.getErrors());
    }

}
