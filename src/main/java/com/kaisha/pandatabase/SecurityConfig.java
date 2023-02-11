package com.kaisha.pandatabase;

import com.kaisha.pandatabase.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//        authenticationManagerBuilder.userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder());

        authenticationManagerBuilder.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorizedRequest) -> authorizedRequest.anyRequest().authenticated())
                .httpBasic(withDefaults());

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}


/*
Both the BCryptPasswordEncoder and the DelegatingPasswordEncoder
returned by PasswordEncoderFactories.createDelegatingPasswordEncoder() are considered safe for password encoding.

BCrypt is a widely used and well-established password hashing function that is considered to be very secure.
It uses a salt and a number of rounds to compute a hash of the password, which makes it more resistant to cracking attempts.

On the other hand, the DelegatingPasswordEncoder returned by PasswordEncoderFactories.createDelegatingPasswordEncoder()
is a flexible password encoder that can delegate to various other password encoders
based on the format of the encoded password. This means that it can be used with different types of password storage systems,
which can be useful in cases where you may need to switch to a different storage system in the future.

In general, both BCrypt and the DelegatingPasswordEncoder are considered safe for password encoding.
The choice between them may depend on the specific requirements of your application, such as compatibility with existing password storage systems and the level of security that you require.
 */
