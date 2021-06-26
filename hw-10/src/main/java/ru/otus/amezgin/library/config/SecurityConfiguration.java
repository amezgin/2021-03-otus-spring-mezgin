package ru.otus.amezgin.library.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @PostConstruct
    public void init() {
        try {
            authenticationManagerBuilder
                    .userDetailsService(userDetailsService)
                    .passwordEncoder(passwordEncoder());
        } catch (Exception e) {
            throw new BeanInitializationException("Security configuration failed", e);
        }
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .httpBasic()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/book/**").authenticated()
                .antMatchers(HttpMethod.GET, "/api/v1/author/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/v1/author/**").hasAnyRole(AuthoritiesConstants.ADMIN)
                .antMatchers(HttpMethod.POST, "/api/v1/author").hasAnyRole(AuthoritiesConstants.ADMIN)
                .antMatchers(HttpMethod.GET, "/api/v1/genre/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/v1/genre/**").hasAnyRole(AuthoritiesConstants.ADMIN)
                .antMatchers(HttpMethod.POST, "/api/v1/genre").hasAnyRole(AuthoritiesConstants.ADMIN)
                .antMatchers("/api/v1/comment/**").authenticated()
                .antMatchers("/**").denyAll()
                .and()
                .formLogin();
    }
}
