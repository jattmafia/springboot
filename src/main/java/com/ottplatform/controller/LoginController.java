package com.ottplatform.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ottplatform.constants.Constants;
import com.ottplatform.entities.RefreshToken;
import com.ottplatform.entities.User;
import com.ottplatform.enums.ErrorCodes;
import com.ottplatform.exception.InvalidRequestException;
import com.ottplatform.exception.TokenExpiredException;
import com.ottplatform.repository.UserRepository;
import com.ottplatform.request.LoginRequest;
import com.ottplatform.request.TokenRefreshRequest;
import com.ottplatform.request.dto.UserRequest;
import com.ottplatform.response.ApiResponse;
import com.ottplatform.response.JwtResponse;
import com.ottplatform.response.TokenRefreshResponse;
import com.ottplatform.response.dto.UserResponse;
import com.ottplatform.service.RefreshTokenService;
import com.ottplatform.service.UserService;
import com.ottplatform.service.impl.UserDetailsImpl;
import com.ottplatform.util.JwtUtils;
import com.ottplatform.util.ResponseBuilder;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    RefreshTokenService refreshTokenService;

    @PostMapping("/sign-in")
    public ApiResponse<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        JwtResponse response = new JwtResponse();
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        RefreshToken refreshToken = null;
        try {
            refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        response = new JwtResponse(Constants.TOKEN_TYPE + " " + jwtToken, refreshToken.getToken(), userDetails.getId(),
                userDetails.getUsername(), userDetails.getEmail());

        return ResponseBuilder.getOkResponse(ErrorCodes.AUTHENTICATION_SUCCESS.getCode(),
                ErrorCodes.AUTHENTICATION_SUCCESS.getMessage(), response);
    }

    @PostMapping("/refresh-token")
    public ApiResponse<TokenRefreshResponse> refreshToken(@Valid @RequestBody TokenRefreshRequest request,
            HttpServletRequest httpServletRequest) throws Exception {
        TokenRefreshResponse tokenRefreshResponse = new TokenRefreshResponse();
        String requestRefreshToken = request.getRefreshToken();
        Optional<RefreshToken> optionalRefreshToken = refreshTokenService.findByToken(requestRefreshToken);

        if (!optionalRefreshToken.isPresent())
            throw new TokenExpiredException(ErrorCodes.REFRESH_TOKEN_NOT_FOUND.getCode());
        String token = null;
        RefreshToken refreshToken = optionalRefreshToken.get();
        if (refreshTokenService.verifyExpiration(refreshToken)) {
            UserResponse response = userService.findByUserId(refreshToken.getUserId()).getData();
            token = jwtUtils.generateTokenFromUsername(response.getUsername());
            tokenRefreshResponse.setAccessToken(token);
            tokenRefreshResponse.setRefreshToken(requestRefreshToken);
            tokenRefreshResponse.setTokenType(Constants.TOKEN_TYPE);
        } else {
            throw new TokenExpiredException(ErrorCodes.REFRESH_TOKEN_EXPIRED.getCode());
        }
        return ResponseBuilder.getOkResponse(ErrorCodes.ACCESS_TOKEN_CREATED_SUCCESSFULLY.getCode(),
                ErrorCodes.ACCESS_TOKEN_CREATED_SUCCESSFULLY.getMessage(), tokenRefreshResponse);
    }

    @PostMapping("/signup")
    public ApiResponse<UserResponse> registerUser(@Valid @RequestBody UserRequest signUpRequest) {
        UserResponse response = new UserResponse();
        User user = new User();
        try {
            user = new User();
            user.setName(signUpRequest.getName());
            user.setStatus(true);
            user.setType("user");
            user.setUsername(signUpRequest.getUsername());
            user.setEmailId(signUpRequest.getEmailId());
            user.setPassword(encoder.encode(signUpRequest.getPassword()));

            user = userRepository.save(user);
            response.setId(user.getId());
            response.setStatus(user.getStatus());
            response.setUsername(user.getUsername());
            response.setEmailId(user.getEmailId());
            response.setMobileNo(user.getMobileNo());

        } catch (DataIntegrityViolationException ex) {
            throw new InvalidRequestException(ErrorCodes.DUPLICATE_EMAIL_MOBILE_USERNAME.getCode());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            throw new InvalidRequestException(ErrorCodes.INVALID_REQUEST.getCode());
        }

        return ResponseBuilder.getOkResponse(ErrorCodes.USER_CREATED_SUCCESSFULLY.getCode(),
                ErrorCodes.USER_CREATED_SUCCESSFULLY.getMessage(), response);
    }
}
