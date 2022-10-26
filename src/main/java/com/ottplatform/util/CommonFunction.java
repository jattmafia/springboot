package com.ottplatform.util;

import com.ottplatform.dto.ErrorDTO;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CommonFunction {

    public ErrorDTO getErrorDTO(String errorCode, String message) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setCode(errorCode);
        errorDTO.setMessage(message);
        return errorDTO;
    }
}
