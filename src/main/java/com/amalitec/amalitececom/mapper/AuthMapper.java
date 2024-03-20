package com.amalitec.amalitececom.mapper;

import com.amalitec.amalitececom.auth.AuthenticationRequest;
import com.amalitec.amalitececom.auth.RegisterRequest;
import com.amalitec.amalitececom.auth.AuthenticationResponse;
import com.amalitec.amalitececom.request_response.graphql.input.AuthenticationInput;
import com.amalitec.amalitececom.request_response.graphql.input.RegisterInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    AuthMapper INSTANCE = Mappers.getMapper(AuthMapper.class);


    RegisterRequest toRegisterRequest(RegisterInput registerInput);


    AuthenticationRequest toAuthenticationRequest(AuthenticationInput authenticationResponse);

    AuthenticationResponse toAuthenticationResponse(RegisterRequest registerRequest);
}
