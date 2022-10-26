package com.ottplatform.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import com.ottplatform.dto.ErrorDTO;

public enum ErrorCodes {

    EmailId_SHOULD_NOT_BE_NULL_OR_BLANK("10006", "Student emailId should not be duplicate", ""),
    DATA_FOUND("10001", "Data found", ""), INTERNAL_SERVER_ERROR("11111", "Internal server error", ""),
    DB_ERROR("10000", "Error occurred while performing operation in database", ""),
    MOBILE_NO_CAN_NOT_BE_DUPLICATE("10014", "Mobile No can not be duplicate", ""),
    OPERATION_PERFORMED_SUCCESS("10001", "Operation performed successfully", ""),
    USER_NAME_SHOULD_NOT_BE_DUPLICATE("10002", "User name should not be duplicate", ""),
    NAME_SHOULD_NOT_BE_NULL_OR_BLANK("10003", "Name should not be blank or null", ""),
    DATA_SAVED_SUCCESSFULLY("10005", "Data saved successfully", ""),
    OPERATION_PERFORMED_FAILURE("20001", "Error occurred while performing operation", ""),
    DELETE_OPERATION_FAILURE("10006", "Delete operation is failure", ""),
    DATA_DELETED_SUCCESSFULLY("10007", "Data deleted successfully", ""), NO_DATA_FOUND("10004", "No Data Found", ""),

    RECORD_NOT_FOUND("10014", "Record not found", ""),
    DATA_UPDATED_SUCCESSFULLY("10017", "Data Updated successfully", ""),

    STUDENT_ID_NOT_EXISTS("10006", "Student id not exists", ""), USER_ID_NOT_EXISTS("10010", "User id not exists", ""),

    ROLE_ID_NOT_EXISTS("10010", "Role id not exists", ""),
    ROLE_NAME_IS_DUPLICATE("10018", "Role name cant be duplicate or blank", ""),

    TOKEN_EXPIRED("10019", "Token is expired, please use refresh token to generate new token", ""),
    REFRESH_TOKEN_EXPIRED("10020", "Refresh token expired", ""),
    REFRESH_TOKEN_NOT_FOUND("10021", "Refresh token not found in DB", ""),
    AUTHENTICATION_SUCCESS("10022", "User authenticated successfully", ""),
    AUTHENTICATION_ERROR("10023", "Invalid username and password", ""),
    ACCESS_TOKEN_CREATED_SUCCESSFULLY("10024", "Access token created successfully", ""),
    USER_CREATED_SUCCESSFULLY("10025", "User created successfully", ""),
    INVALID_REQUEST("10026", "Invalid request", ""),
    DUPLICATE_EMAIL_MOBILE_USERNAME("10027", "Record is already exist for email or mobile or username", "");

    protected static final Map<String, ErrorCodes> errorMap = Arrays.stream(values())
            .collect(Collectors.toMap(ErrorCodes::getCode, x -> x, (x, y) -> x));

    private final String code;

    private final String message;

    private final String description;

    ErrorCodes(String code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;

    }

    public static ErrorCodes getEnum(String code) {
        return errorMap.get(code);
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }

    public ErrorDTO getError(Object... objects) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setCode(code);
        errorDTO.setMessage(String.format(message, objects));
        return errorDTO;
    }

};
