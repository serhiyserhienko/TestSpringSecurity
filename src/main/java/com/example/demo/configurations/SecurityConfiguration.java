package com.example.demo.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity//   (debug = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public static BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("a").password("a").roles("ADMIN").and()
                .withUser("u").password("u").roles("USER").and()
                .passwordEncoder(new PasswordEncoder() {
                    @Override
                    public String encode(CharSequence rawPassword) {
                        return rawPassword.toString();
                    }

                    @Override
                    public boolean matches(CharSequence rawPassword, String encodedPassword) {
                        return rawPassword.equals(encodedPassword);
                    }
                });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                    .disable()
                .authorizeRequests()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .permitAll()
                    .and()
                .logout()
                    .logoutSuccessUrl("/login")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .permitAll()
                    .and()
                .exceptionHandling()
                    .accessDeniedPage("/access-denied.html");
    }
}
