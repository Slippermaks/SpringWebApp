package com.slipper.SpringWebApp.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

// Конфигурация спринга
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private DataSource dataSource;

    // Привязка источника даты
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Конфигурация аутентификации
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .usersByUsernameQuery("select username, password, enabled from users " +
                        "where username=?")
                .authoritiesByUsernameQuery("select username, authority from authorities " +
                        "where username=?");
    }

    // Конфигурация доступа к определённым путям по роли
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/details/**").hasAnyRole("ADMIN", "USER")
                    .antMatchers("/cart/create_order/**").hasAnyRole("ADMIN", "USER")
                    .antMatchers("/admin/**").hasAnyRole("ADMIN")
                    .anyRequest().permitAll()
                .and()
                    .formLogin()
                    .loginPage("/login").permitAll()
                    .loginProcessingUrl("/authenticateTheUser");
    }


}
