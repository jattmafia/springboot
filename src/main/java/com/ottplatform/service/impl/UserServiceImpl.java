package com.ottplatform.service.impl;

import com.ottplatform.entities.User;
import com.ottplatform.enums.ErrorCodes;
import com.ottplatform.repository.UserRepository;
import com.ottplatform.request.dto.UserRequest;
import com.ottplatform.response.ApiResponse;
import com.ottplatform.response.dto.UserResponse;
import com.ottplatform.service.UserService;
import com.ottplatform.util.CommonFunction;
import com.ottplatform.util.ResponseBuilder;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public ApiResponse<UserResponse> saveUser(UserRequest userRequest) {
        log.info("Inside save method of UserServiceImpl");

        Optional<User> optionalUser = userRepository.findByEmailId(userRequest.getEmailId());
        if (optionalUser.isPresent())
            return ResponseBuilder.getOkResponse(ErrorCodes.EmailId_SHOULD_NOT_BE_NULL_OR_BLANK.getCode(),
                    ErrorCodes.EmailId_SHOULD_NOT_BE_NULL_OR_BLANK.getMessage(), null);
        Optional<User> optionalUser1 = userRepository.findByMobileNo(userRequest.getMobileNo());
        if (optionalUser1.isPresent())
            return ResponseBuilder.getOkResponse(ErrorCodes.MOBILE_NO_CAN_NOT_BE_DUPLICATE.getCode(),
                    ErrorCodes.MOBILE_NO_CAN_NOT_BE_DUPLICATE.getMessage(), null);
        try {
            User user = new User();
            user.setEmailId(userRequest.getEmailId());
            user.setMobileNo(userRequest.getMobileNo());
            user.setStatus(userRequest.getStatus());
            user.setUsername(userRequest.getUsername());
            user.setPassword(userRequest.getPassword());
            user = userRepository.save(user);
            log.info("End save method UserServiceImpl");
            UserResponse userResponse = new UserResponse();
            setResponse(user, userResponse);
            return ResponseBuilder.getOkResponse(ErrorCodes.OPERATION_PERFORMED_SUCCESS.getCode(),
                    ErrorCodes.OPERATION_PERFORMED_SUCCESS.getMessage(), userResponse);
        } catch (Exception ex) {
            if (!StringUtils.isBlank(ex.getMessage())
                    && ex.getMessage().equalsIgnoreCase(ErrorCodes.NO_DATA_FOUND.getCode()))
                return ResponseBuilder.getErrorResponse(CommonFunction.getErrorDTO(ErrorCodes.NO_DATA_FOUND.getCode(),
                        ErrorCodes.NO_DATA_FOUND.getMessage()));
            else
                return ResponseBuilder.getErrorResponse(
                        CommonFunction.getErrorDTO(ErrorCodes.DB_ERROR.getCode(), ErrorCodes.DB_ERROR.getMessage()));
        }

    }

    @Override
    public ApiResponse<UserResponse> findByUserId(Long id) {
        try {
            Optional<UserResponse> userResponse = userRepository.findByUserId(id);
            if (userResponse.isPresent())
                return ResponseBuilder.getOkResponse(ErrorCodes.DATA_FOUND.getCode(),
                        ErrorCodes.DATA_FOUND.getMessage(), userResponse.get());
            else
                return ResponseBuilder.getErrorResponse(CommonFunction.getErrorDTO(ErrorCodes.NO_DATA_FOUND.getCode(),
                        ErrorCodes.NO_DATA_FOUND.getMessage()));

        } catch (Exception ex) {
            if (!StringUtils.isBlank(ex.getMessage())
                    && ex.getMessage().equalsIgnoreCase(ErrorCodes.NO_DATA_FOUND.getCode()))
                return ResponseBuilder.getErrorResponse(CommonFunction.getErrorDTO(ErrorCodes.NO_DATA_FOUND.getCode(),
                        ErrorCodes.NO_DATA_FOUND.getMessage()));
            else
                return ResponseBuilder.getErrorResponse(
                        CommonFunction.getErrorDTO(ErrorCodes.DB_ERROR.getCode(), ErrorCodes.DB_ERROR.getMessage()));
        }
    }

    @Override
    public ApiResponse<String> updateUser(Long id, UserRequest userRequest) {
        log.info("Inside Update method of UserServiceImpl");
        Optional<User> optionalUser = userRepository.findById(id);
        if (!Optional.ofNullable(optionalUser).isPresent())
            return ResponseBuilder.getErrorResponse(CommonFunction.getErrorDTO(ErrorCodes.NO_DATA_FOUND.getCode(),
                    ErrorCodes.NO_DATA_FOUND.getMessage()));
        try {
            User user = optionalUser.get();
            user.setId(id);
            user.setEmailId(userRequest.getEmailId());
            user.setMobileNo(userRequest.getMobileNo());
            user.setStatus(userRequest.getStatus());
            user.setUsername(userRequest.getUsername());
            user.setPassword(userRequest.getPassword());
            userRepository.save(user);
            log.info("End save method UserServiceImpl");
        } catch (Exception ex) {
            if (!StringUtils.isBlank(ex.getMessage())
                    && ex.getMessage().equalsIgnoreCase(ErrorCodes.NO_DATA_FOUND.getCode()))
                return ResponseBuilder.getErrorResponse(CommonFunction.getErrorDTO(ErrorCodes.NO_DATA_FOUND.getCode(),
                        ErrorCodes.NO_DATA_FOUND.getMessage()));
            else
                return ResponseBuilder.getErrorResponse(
                        CommonFunction.getErrorDTO(ErrorCodes.DB_ERROR.getCode(), ErrorCodes.DB_ERROR.getMessage()));
        }
        return ResponseBuilder.getOkResponse(ErrorCodes.DATA_UPDATED_SUCCESSFULLY.getCode(),
                ErrorCodes.DATA_UPDATED_SUCCESSFULLY.getMessage(), null);
    }

    @Override
    public ApiResponse<List<UserResponse>> findAllUser() {
        List<UserResponse> userResponseList = null;// userRepository.findAllUser();
        return ResponseBuilder.getOkResponse(ErrorCodes.DATA_FOUND.getCode(), ErrorCodes.DATA_FOUND.getMessage(),
                userResponseList);
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public ApiResponse<String> deleteUser(Long id) {
        ApiResponse<UserResponse> apiResponse = findByUserId(id);
        if (!Optional.ofNullable(apiResponse).isPresent())
            return ResponseBuilder.getErrorResponse(CommonFunction.getErrorDTO(ErrorCodes.RECORD_NOT_FOUND.getCode(),
                    ErrorCodes.RECORD_NOT_FOUND.getMessage()));
        try {
            userRepository.deleteById(id);
        } catch (Exception ex) {
            if (!StringUtils.isBlank(ex.getMessage())
                    && ex.getMessage().equalsIgnoreCase(ErrorCodes.RECORD_NOT_FOUND.getCode()))
                return ResponseBuilder.getErrorResponse(CommonFunction
                        .getErrorDTO(ErrorCodes.RECORD_NOT_FOUND.getCode(), ErrorCodes.RECORD_NOT_FOUND.getMessage()));
            else
                return ResponseBuilder.getErrorResponse(
                        CommonFunction.getErrorDTO(ErrorCodes.DB_ERROR.getCode(), ErrorCodes.DB_ERROR.getMessage()));

        }
        return ResponseBuilder.getOkResponse(ErrorCodes.DATA_DELETED_SUCCESSFULLY.getCode(),
                ErrorCodes.DATA_DELETED_SUCCESSFULLY.getMessage(), null);
    }

    private void setResponse(User user, UserResponse userResponse) {
        userResponse.setId(user.getId());

    }

}
