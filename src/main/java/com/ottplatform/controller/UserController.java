package com.ottplatform.controller;

import com.ottplatform.enums.ErrorCodes;
import com.ottplatform.request.dto.UserRequest;
import com.ottplatform.response.ApiResponse;
import com.ottplatform.response.dto.UserResponse;
import com.ottplatform.service.UserService;
import com.ottplatform.util.CommonFunction;
import com.ottplatform.util.ResponseBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ApiResponse<UserResponse> saveUser(@RequestBody UserRequest userRequest) {
        if (userRequest.getMobileNo().length() > 10)
            return ResponseBuilder
                    .getErrorResponse(CommonFunction.getErrorDTO(ErrorCodes.OPERATION_PERFORMED_FAILURE.getCode(),
                            ErrorCodes.OPERATION_PERFORMED_FAILURE.getMessage()));
        return userService.saveUser(userRequest);
    }

    @GetMapping("{id}")
    public ApiResponse<UserResponse> findByUserId(@PathVariable("id") Long id) {
        return userService.findByUserId(id);
    }

    @PutMapping("{id}")
    public ApiResponse<String> updateUser(@PathVariable("id") Long id, @RequestBody UserRequest userRequest) {
        return userService.updateUser(id, userRequest);
    }

    @DeleteMapping("{id}")
    public ApiResponse<String> deleteUser(@PathVariable("id") Long id) {

        return userService.deleteUser(id);
    }

    @GetMapping
    public ApiResponse<List<UserResponse>> findAllUser() {
        return userService.findAllUser();
    }

}
