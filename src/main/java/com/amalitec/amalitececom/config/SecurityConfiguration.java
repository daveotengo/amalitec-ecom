package com.amalitec.amalitececom.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;


import static com.amalitec.amalitececom.auth.Permission.*;
import static com.amalitec.amalitececom.auth.Role.*;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

  private final JwtAuthenticationFilter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;
  private final LogoutHandler logoutHandler;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http.headers().frameOptions().disable(); //for h2 db ui display
    http

        .csrf()
        .disable()
        .authorizeHttpRequests()
        .requestMatchers(
                "/api/v1/auth/**",
                "/v2/api-docs",
                "/v3/api-docs",
                "/v3/api-docs/**",
                "/swagger-resources",
                "/swagger-resources/**",
                "/configuration/ui",
                "/configuration/security",
                "/swagger-ui/**",
                "/webjars/**",
                "/swagger-ui.html"
//                "/vendor/playground/**",
//                "/vendor/graphiql/**",
//                "/vendor/voyager/**"



        )
        .permitAll()
        .and()
        .authorizeHttpRequests()
        .requestMatchers("/favicon.ico").permitAll()
        .requestMatchers("/h2-console/**").permitAll()
        .requestMatchers("/graphql/**").permitAll()
        .requestMatchers("/graphiql/**").permitAll()
        .requestMatchers("/voyager/**").permitAll()
        .requestMatchers("/playground/**").permitAll()

//        .requestMatchers("/graphql/**").hasAnyRole(ADMIN.name(), MANAGER.name())
//
//        .requestMatchers(GET, "/graphql/**").hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name())
//        .requestMatchers(POST, "/graphql/**").hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name())
//        .requestMatchers(PUT, "/graphql/**").hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name())
//        .requestMatchers(DELETE, "/graphql/**").hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name())
//        .requestMatchers(GET, "/graphql/**").hasAuthority(ADMIN_READ.name())
       /* .requestMatchers("/graphql/**").hasRole(ADMIN.name())

        .requestMatchers(GET, "/graphql/**").hasAuthority(ADMIN_READ.name())

        .requestMatchers(PUT, "/graphql/**").hasAuthority(ADMIN_UPDATE.name())
        .requestMatchers(DELETE, "/graphql/**").hasAuthority(ADMIN_DELETE.name())*/


        .anyRequest()
          .authenticated()
        .and()
          .sessionManagement()
          .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        .logout()
        .logoutUrl("/auth/logout")
        .addLogoutHandler(logoutHandler)
        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
    ;

    return http.build();
  }
}
