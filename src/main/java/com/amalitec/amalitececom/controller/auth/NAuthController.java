package com.amalitec.amalitececom.controller.auth;

import com.amalitec.amalitececom.auth.AuthenticationRequest;
import com.amalitec.amalitececom.auth.AuthenticationResponse;
import com.amalitec.amalitececom.auth.AuthenticationService;
import com.amalitec.amalitececom.auth.RegisterRequest;
import com.amalitec.amalitececom.mapper.AuthMapper;
import com.amalitec.amalitececom.request_response.graphql.input.AuthenticationInput;
import com.amalitec.amalitececom.request_response.graphql.input.RegisterInput;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Controller
public class NAuthController {


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





}
