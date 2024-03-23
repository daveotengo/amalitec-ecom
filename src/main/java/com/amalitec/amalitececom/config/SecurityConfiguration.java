package com.amalitec.amalitececom.config;

import jakarta.servlet.DispatcherType;
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
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.savedrequest.NullRequestCache;


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
        http.headers().frameOptions().disable();
        //http.cors().disable();
        http.authorizeHttpRequests(authorizeRequests ->
        authorizeRequests
              .dispatcherTypeMatchers(DispatcherType.ASYNC).permitAll()
        );


        http.csrf().disable();
        //http.securityMatcher("/graphql/**").authorizeHttpRequests();
            http

        .authorizeHttpRequests()
              // Allow public access to certain endpoints
              .requestMatchers(
                      "/auth/**",
                      "/v2/api-docs",
                      "/v3/api-docs",
                      "/v3/api-docs/**",
                      "/swagger-resources",
                      "/swagger-resources/**",
                      "/configuration/ui",
                      "/configuration/security",
                      "/swagger-ui/**",
                      "/webjars/**",
                      "/swagger-ui.html",
                      "/favicon.ico",
                      "/h2-console/**",
                      "/graphiql/**",
                      "/voyager/**",
                      "/playground/**",
                      "/vendor/**",
                      "/graphql/**"
              ).permitAll()
             // Require authentication for /graphql
                    .anyRequest().authenticated()
//                    .and()
//                    .requestCache()
//                    .requestCache(new NullRequestCache())
//                    .and()
//                    .headers()
//                    .frameOptions().sameOrigin() // needed for H2 web console
                    //.requestMatchers("/graphql/**").authenticated()
                    .and()
          .sessionManagement()
          .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
//        .securityContext((securityContext) -> securityContext
//                .securityContextRepository(new DelegatingSecurityContextRepository(
//                        new RequestAttributeSecurityContextRepository(),
//                        new HttpSessionSecurityContextRepository()
//                )))

        .logout()
        .logoutUrl("/auth/logout")
        .addLogoutHandler(logoutHandler)
        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())

    ;

    return http.build();
  }
}
