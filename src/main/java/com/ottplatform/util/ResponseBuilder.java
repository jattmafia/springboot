package com.ottplatform.util;

import com.ottplatform.constants.Constants;
import com.ottplatform.dto.ErrorDTO;
import com.ottplatform.response.ApiResponse;

import java.util.Collections;
import java.util.List;

public class ResponseBuilder {

    private ResponseBuilder() {

    }

    public static <T> ApiResponse<T> getOkResponse(String code, String message, T data) {
        return new ApiResponse<>(code, message, data, null, Constants.SUCCESS_200);
    }

    public static <T> ApiResponse<T> getOkResponse(String code, String message, T data, int status) {
        return new ApiResponse<>(code, message, data, null, status);
    }

    public static <T> ApiResponse<T> getErrorResponse(ErrorDTO errorDTO) {
        return new ApiResponse<>(null, null, null, Collections.singletonList(errorDTO), Constants.FAILURE_400);
    }

    public static <T> ApiResponse<T> getErrorResponse(List<ErrorDTO> errorDTOs) {
        return new ApiResponse<>(null, null, null, errorDTOs, Constants.FAILURE_400);
    }
}
