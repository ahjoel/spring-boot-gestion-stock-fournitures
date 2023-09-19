package com.gestock.fourniture.rest.ressource;

import com.gestock.fourniture.exceptions.CustomException;
import com.gestock.fourniture.model.dto.request.SignUpRequest;
import com.gestock.fourniture.model.dto.request.SigninRequest;
import com.gestock.fourniture.model.dto.response.JwtAuthenticationResponse;
import com.gestock.fourniture.model.entities.ErrorResponse;
import com.gestock.fourniture.service.security.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationControllerRest {
    private final AuthenticationService authenticationService;

    /**@PostMapping("/signups")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }*/

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest request) {
        try {
            JwtAuthenticationResponse response = authenticationService.signup(request);
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            // Handle the custom exception and return an error response
            ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode(), e.getMessage());
            return ResponseEntity.status(e.getHttpStatus()).body(errorResponse);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SigninRequest request) {
        try {
            JwtAuthenticationResponse response = authenticationService.signin(request);
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            // Handle the custom exception and return an error response
            ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode(), e.getMessage());
            return ResponseEntity.status(e.getHttpStatus()).body(errorResponse);
        }
    }
}