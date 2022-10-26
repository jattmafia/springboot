package com.ottplatform.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ottplatform.constants.Constants;
import com.ottplatform.dto.ErrorDTO;
import com.ottplatform.enums.ErrorCodes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {

        String errorMessage = authException.getMessage();
        logger.error("Unauthorized error: {}", errorMessage);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_OK);

        final Map<String, Object> body = new HashMap<>();
        body.put("status", Constants.FAILURE_400);

        final String expired = (String) request.getAttribute(ErrorCodes.TOKEN_EXPIRED.getCode());
        final String refreshToken = (String) request.getAttribute(ErrorCodes.REFRESH_TOKEN_EXPIRED.getCode());

        List<ErrorDTO> errorDTOs = new ArrayList<ErrorDTO>();
        ErrorDTO errorDTO = new ErrorDTO();
        String code = "";
        String message = "";
        if (null != expired) {
            message = ErrorCodes.TOKEN_EXPIRED.getMessage();
            code = ErrorCodes.TOKEN_EXPIRED.getCode();
        } else if (null != refreshToken) {
            message = ErrorCodes.REFRESH_TOKEN_EXPIRED.getMessage();
            code = ErrorCodes.REFRESH_TOKEN_EXPIRED.getCode();
        } else if (StringUtils.hasText(errorMessage) && errorMessage.contains("Bad credentials")) {
            code = ErrorCodes.AUTHENTICATION_ERROR.getCode();
            message = ErrorCodes.AUTHENTICATION_ERROR.getMessage();
        } else {
            code = ErrorCodes.AUTHENTICATION_ERROR.getCode();
            message = authException.getMessage();
        }

        if (message == null)
            message = ErrorCodes.AUTHENTICATION_ERROR.getMessage();

        errorDTO.setCode(code);
        errorDTO.setMessage(message);
        errorDTOs.add(errorDTO);

        body.put("errors", errorDTOs);

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), body);

    }
}