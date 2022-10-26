package com.ottplatform.service;

import com.ottplatform.entities.User;
import com.ottplatform.request.dto.UserRequest;
import com.ottplatform.response.ApiResponse;
import com.ottplatform.response.dto.UserResponse;

import java.util.List;

public interface UserService {

    ApiResponse<UserResponse> saveUser(UserRequest userRequest);

    ApiResponse<UserResponse> findByUserId(Long id);

    ApiResponse<String> updateUser(Long id, UserRequest userRequest);

    ApiResponse<List<UserResponse>> findAllUser();

    User findByUserName(String userName);

    ApiResponse<String> deleteUser(Long id);

}
