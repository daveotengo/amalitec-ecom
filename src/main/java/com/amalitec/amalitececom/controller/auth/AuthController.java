package com.amalitec.amalitececom.controller.auth;

import com.amalitec.amalitececom.auth.AuthenticationRequest;
import com.amalitec.amalitececom.auth.AuthenticationResponse;
import com.amalitec.amalitececom.auth.AuthenticationService;
import com.amalitec.amalitececom.auth.RegisterRequest;
import com.amalitec.amalitececom.config.JwtService;
import com.amalitec.amalitececom.config.LogoutService;
import com.amalitec.amalitececom.mapper.AuthMapper;
import com.amalitec.amalitececom.request_response.graphql.input.AuthenticationInput;
import com.amalitec.amalitececom.request_response.graphql.input.RegisterInput;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@RequiredArgsConstructor
@Controller
public class AuthController{


    private final AuthenticationService authService;
    private final AuthMapper authMapper;



    @MutationMapping
    public AuthenticationResponse register(@Argument RegisterInput input) {
        RegisterRequest request= authMapper.toRegisterRequest(input);
        return authService.register(request);
    }

    @MutationMapping
    public AuthenticationResponse authenticate(@Argument AuthenticationInput input) {
        AuthenticationRequest request = authMapper.toAuthenticationRequest(input);
        return authService.authenticate(request);
    }

    @MutationMapping
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authService.refreshToken(request, response);
    }



}
