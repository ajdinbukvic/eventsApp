package com.ptf.si.wp.zadaca1.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.ptf.si.wp.zadaca1.services.impl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

  @Autowired
  private UserDetailsServiceImpl _customUserDetailsService;

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.csrf()
        .disable()
        .authorizeHttpRequests()
        .antMatchers("/h2-console/**").permitAll()
        .antMatchers("/register/**", "/home/**", "/event/**").permitAll()
        .antMatchers("/css/**", "/js/**").permitAll()
        .antMatchers("/user-manage", "/event-manage", "/location-manage", "/category-manage").hasAuthority("ADMIN")
        .antMatchers("/user-manage", "/event-manage", "/location-manage", "/category-manage").hasAuthority("ADMIN")
        .antMatchers("/categories/**", "/locations/**", "/events/**", "/users/changestatus/**").hasAuthority("ADMIN")
        .antMatchers("/profile/**", "/users/changepassword/**", "/comments/**").hasAuthority("USER")
        .anyRequest().authenticated()
        .and()
        .formLogin(form -> form
            .loginPage("/login")
            .loginProcessingUrl("/login")
            .failureUrl("/login?error")
            .usernameParameter("username")
            .passwordParameter("password")
            .defaultSuccessUrl("/home", true)
            .permitAll())
        .logout(
            logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .clearAuthentication(true)
                .permitAll())
        .rememberMe()
        .key("security-remember-secret")
        .tokenValiditySeconds(86400)
        .and()
        .userDetailsService(_customUserDetailsService)
        .build();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
