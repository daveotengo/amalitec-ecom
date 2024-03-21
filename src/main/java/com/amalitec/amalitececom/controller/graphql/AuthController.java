package com.amalitec.amalitececom.controller.graphql;

import com.amalitec.amalitececom.auth.AuthenticationService;
import com.amalitec.amalitececom.config.JwtService;
import com.amalitec.amalitececom.config.LogoutService;

import com.amalitec.amalitececom.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final LogoutService logoutService;
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final JwtService jwtService;
    private final UserService userService;

    private final AuthenticationService authService;

    @MutationMapping
    public Boolean logout() {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7); // Remove "Bearer " prefix
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String userEmail = jwtService.extractUsername(token);
            var user = this.userService.getByEmail(userEmail)
                    .orElseThrow();
            if (auth != null&&jwtService.isTokenValid(token,user)){
                String username = SecurityContextHolder.getContext().getAuthentication().getName();

                System.out.println("User " + username + " logged out.");
                logoutService.logout(request,response,auth);
                return true; // Indicate successful logout
            } else {
                return false; // No user logged in, return false
            }
        }
        return  false;
    }


    @MutationMapping
    public void refreshToken() throws IOException {
        authService.refreshToken(request, response);
    }
}
