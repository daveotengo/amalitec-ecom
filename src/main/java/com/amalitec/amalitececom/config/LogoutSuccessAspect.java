package com.amalitec.amalitececom.config;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

//@Aspect
//@Component
//public class LogoutSuccessAspect {
//
//    @AfterReturning(pointcut = "execution(* com.amalitec.amalitececom.controller.graphql.AuthController.logout(..)) && args(authentication, ..)", returning = "result")
//    public void handleLogoutSuccess(Authentication authentication, Object result) {
//        System.out.println("=authentication=");
//        System.out.println(authentication);
//        System.out.println(result);
//        // Clear the security context after successful logout
//        SecurityContextHolder.clearContext();
//    }
//}
