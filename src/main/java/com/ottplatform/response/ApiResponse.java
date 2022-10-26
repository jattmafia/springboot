package com.ottplatform.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ottplatform.dto.ErrorDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private String code;

    private String message;

    private T data;

    private List<ErrorDTO> errors;

    private int status;

}
