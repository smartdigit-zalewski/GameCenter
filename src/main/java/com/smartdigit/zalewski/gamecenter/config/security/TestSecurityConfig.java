package com.smartdigit.zalewski.gamecenter.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * Created by SmartDigit Michal Zalewski
 * Date: 26.01.2022
 */
@Configuration
@Profile("test")
public class TestSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;


    private enum role{
        ADMIN,
        USER
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {


        auth.jdbcAuthentication().dataSource(dataSource).withDefaultSchema()
                .withUser(User.withUsername("admin")
                .password("{noop}1234")
                .roles(role.ADMIN.toString()));
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/resources/static/css/**").permitAll()
                .antMatchers("/resources/static/js/**").permitAll()
                .antMatchers("/actuator/**").hasRole(TestSecurityConfig.role.ADMIN.toString())
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll();
        http.headers().frameOptions().disable();
    }
}
