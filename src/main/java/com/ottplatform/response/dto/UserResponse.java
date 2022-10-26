package com.ottplatform.response.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    private Long id;

    private String name;

    private String emailId;

    private String mobileNo;

    private Boolean status;

    private String username;

    private Date createdOn;

    private Date updatedOn;

    public UserResponse(Long id, String name, String emailId, String mobileNo, Boolean status, String username) {
        this.id = id;
        this.name = name;
        this.emailId = emailId;
        this.mobileNo = mobileNo;
        this.status = status;
        this.username = username;
    }

}
