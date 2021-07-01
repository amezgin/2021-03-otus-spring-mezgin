package ru.otus.amezgin.library.config;

import lombok.RequiredArgsConstructor;
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

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
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
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/book/**").authenticated()
                .antMatchers(HttpMethod.GET, "/api/v1/author/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/v1/author/**").hasAnyRole(RoleConstants.ADMIN)
                .antMatchers(HttpMethod.POST, "/api/v1/author").hasAnyRole(RoleConstants.ADMIN)
                .antMatchers(HttpMethod.GET, "/api/v1/genre/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/v1/genre/**").hasAnyRole(RoleConstants.ADMIN)
                .antMatchers(HttpMethod.POST, "/api/v1/genre").hasAnyRole(RoleConstants.ADMIN)
                .antMatchers("/api/v1/comment/**").authenticated()
                .antMatchers("/login").permitAll()
                .antMatchers("/**").denyAll()
                .and()
                .formLogin();
    }
}
