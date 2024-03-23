package com.amalitec.amalitececom.config;

import com.amalitec.amalitececom.repository.TokenRepository;
import com.amalitec.amalitececom.repository.UserRepository;
import graphql.GraphQLException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.amalitec.amalitececom.exception.UnauthorizedException;


@Aspect
@Component
@RequiredArgsConstructor
public class GraphQLSecurityAspect {

    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;


    @Before("execution(* com.amalitec.amalitececom.controller.graphql.*.*(..))")
    public void beforeGraphQLMethodExecution(JoinPoint joinPoint) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            System.out.println("Authentication: " + authentication);



        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            throw new UnauthorizedException("Request attributes are null");
        }

        HttpServletRequest request = attributes.getRequest();
        String token = request.getHeader("Authorization");
        //Bearer

        if (token == null || token.isEmpty()) {
            throw new UnauthorizedException("Missing authorization token");
        }

        String jwt = token.substring(7);
        System.out.println("printing jwt");
        System.out.println(jwt);
        String userEmail = jwtService.extractUsername(jwt);
        System.out.println(userEmail);
        //&& SecurityContextHolder.getContext().getAuthentication() == null
        if (userEmail != null ) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            var isTokenValid = tokenRepository.findByToken(jwt)
                    .map(t -> !t.isExpired() && !t.isRevoked())
                    .orElse(false);
            if (jwtService.isTokenValid(jwt, userDetails) && isTokenValid) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            } else {
                throw new UnauthorizedException("Invalid authorization token");
            }
        } else {
            throw new UnauthorizedException("Invalid or missing authorization token");
        }
            System.out.println("Authentication succeeded for method: " + joinPoint.getSignature());
        } catch (Exception e) {
            System.out.println("Error during authentication: " + e.getMessage());
            throw e; // Rethrow the exception to propagate it
        }
    }


   
}

