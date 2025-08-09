package org.lessons.java.spring_la_mia_pizzeria_crud.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) 
    throws Exception {
        http.authorizeHttpRequests(requests -> requests
        .requestMatchers("/pizzas/create", "/pizzas/edit/**").hasAuthority("ADMIN")
        .requestMatchers(HttpMethod.POST, "/pizzas/**").hasAuthority("ADMIN")
        .requestMatchers("/ingredients", "/ingredients/**").hasAuthority("ADMIN")
        .requestMatchers("/pizzas", "/pizzas/**").hasAnyAuthority("USER", "ADMIN")
        .requestMatchers("/**").permitAll())
        .formLogin(Customizer.withDefaults())
        .logout(Customizer.withDefaults())
        .exceptionHandling(Customizer.withDefaults())
        .cors(cors -> cors.disable())
        .csrf(csrf -> csrf.disable());
        

        return http.build();
    }

    @Bean
    @SuppressWarnings("deprecation")
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService());
        return provider;
    }

    @Bean
    DatabaseUserDetailService userDetailsService() {
        return new DatabaseUserDetailService();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
}
}
