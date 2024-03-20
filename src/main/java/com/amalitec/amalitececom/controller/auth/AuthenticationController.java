//package com.amalitec.amalitececom.controller.rest;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import com.amalitec.amalitececom.auth.AuthenticationRequest;
//import com.amalitec.amalitececom.auth.AuthenticationResponse;
//import com.amalitec.amalitececom.auth.AuthenticationService;
//import com.amalitec.amalitececom.auth.RegisterRequest;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//
//@RestController
//@RequestMapping("/auth")
//@RequiredArgsConstructor
//public class AuthenticationController {
//
//  private final AuthenticationService service;
//
//  @PostMapping("/register")
//  public ResponseEntity<AuthenticationResponse> register(
//      @RequestBody RegisterRequest request
//  ) {
//    return ResponseEntity.ok(service.register(request));
//  }
//  @PostMapping("/sign-in")
//  public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
//    return ResponseEntity.ok(service.authenticate(request));
//  }
//
//  @PostMapping("/refresh-token")
//  public void refreshToken(
//      HttpServletRequest request,
//      HttpServletResponse response
//  ) throws IOException {
//    service.refreshToken(request, response);
//  }
//
//
//}
