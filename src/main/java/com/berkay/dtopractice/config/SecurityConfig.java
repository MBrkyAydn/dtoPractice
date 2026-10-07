package com.berkay.dtopractice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
//                        .requestMatchers("/student/**").permitAll()
                                .requestMatchers("/student").permitAll()
                                .requestMatchers("/auth/register").permitAll()
                                .requestMatchers("/student/1").hasRole("ADMIN")
                                .anyRequest().authenticated()  ///student/1 endpoint'ine yalnızca ADMIN rolüne sahip kullanıcı girebilir.
                )
                .httpBasic(Customizer.withDefaults()); // UserDetailsService Beani gördüğünde
        //bunu authentication sürecinde kullanabilir.Fakat şu anda Basic Auth ile test etmek için httpBasic() eklememiz gerekecek.


        return http.build();
    }

}

