package ks.msx.BookStore.config;

import ks.msx.BookStore.config.filter.JwtAuthFilter;
import ks.msx.BookStore.controller.MainController;
import ks.msx.BookStore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebConfig {
    private final JwtEntryPoint authEntryPoint;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/",
                                MainController.END_POINT,
                                MainController.END_POINT+"/logout",
                                MainController.END_POINT+"/login",
                                MainController.END_POINT+"/login/log",
                                MainController.END_POINT+"/register",
                                MainController.END_POINT+"/register/reg",
                                MainController.END_POINT+"/test"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                //.exceptionHandling(handling -> handling.authenticationEntryPoint(authEntryPoint))
                //.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //.addFilterBefore(new JwtAuthFilter(), UsernamePasswordAuthenticationFilter.class)
                .formLogin(form -> form
                        .loginPage(MainController.END_POINT+"/login").permitAll()
                        .loginProcessingUrl(MainController.END_POINT+"/login/log").permitAll()
                        .successForwardUrl("/").permitAll());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
