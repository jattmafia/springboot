package com.ottplatform.request.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRequest {

    private String name;

    private String emailId;

    private String mobileNo;

    private String username;

    private String password;

    private String type;

    private Boolean status;

}
