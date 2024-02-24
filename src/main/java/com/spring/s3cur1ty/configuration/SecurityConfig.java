package com.spring.s3cur1ty.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authProvider;
//    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .cors().and()
//                .csrf(AbstractHttpConfigurer::disable)
                .csrf().disable()
//                .csrf().ignoringRequestMatchers("/api/v1/auth/**")
//                    .csrfTokenRepository(CookieCsrfTokenRepository
//                            .withHttpOnlyFalse())
//                    .and()
                .authorizeHttpRequests( auth -> auth
                        .requestMatchers("/api/v1/auth/**").permitAll()
                                .requestMatchers("/api/test/***").authenticated()

                        .anyRequest().authenticated()
//                                .anyRequest().permitAll()
                )
                .sessionManagement(sess->sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//                .logout(logout ->
//                        logout.logoutUrl("/api/v1/auth/logout")
//                                .addLogoutHandler(logoutHandler)
//                                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
//                );
//                .logout(logout -> logout
//                        .logoutUrl("/api/v1/auth/logout")
//                        .logoutSuccessUrl("/api/v1/auth/login")
//                        .invalidateHttpSession(true)
//                        .deleteCookies("JSESSIONID")
//                );

        return http.build();
    }

//    @Bean
//    public CorsFilter corsFilter() {
////        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////        CorsConfiguration config = new CorsConfiguration();
////        config.addAllowedOrigin("http://localhost:4200/**");
////        // Allow all origins, headers, and methods. This is just an example; adjust it based on your needs.
//////        config.addAllowedOrigin("*");
//////        config.addAllowedHeader("*");
//////        config.addAllowedMethod("*");
//////        source.registerCorsConfiguration("/**", config);
////        return new CorsFilter(source);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        // Allow all origins, headers, and methods. Adjust these based on your needs.
//        config.addAllowedOrigin("http://localhost:4200");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        // Allow credentials (cookies, authorization headers)
//        config.setAllowCredentials(true);
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);
//    }
}
