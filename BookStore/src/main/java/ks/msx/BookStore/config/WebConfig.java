package ks.msx.BookStore.config;

import ks.msx.BookStore.controller.MainController;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebConfig {

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
                                MainController.END_POINT+"/register/reg").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form.permitAll()
                        .loginPage(MainController.END_POINT+"/login")
                        .successForwardUrl("/"));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }
}
