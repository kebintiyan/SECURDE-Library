package com.securde.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
public class LibrarySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private LibraryAuthenticationSuccessHandler libraryAuthenticationSuccessHandler;

    @Autowired
    private LibraryAuthenticationFailureHandler libraryAuthenticationFailureHandler;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.
                jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/fonts/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/test").permitAll()
                .antMatchers("/search/**").permitAll()
                .antMatchers("/text/**").hasAnyAuthority("STUDENT", "FACULTY")
                .antMatchers("/user/**").hasAnyAuthority("STUDENT", "FACULTY")
                .antMatchers("/manager/**").hasAuthority("MANAGER")
                .antMatchers("/staff/**").hasAuthority("STAFF")
                .antMatchers("/admin/**").hasAuthority("ADMINISTRATOR")
                .anyRequest()
                .authenticated()
                .and()
                /*.csrf()
                .disable()*/
                .formLogin()
                .loginPage("/login")
                .failureHandler(libraryAuthenticationFailureHandler)
//                .successHandler(libraryAuthenticationSuccessHandler)
                .defaultSuccessUrl("/home")
                .usernameParameter("username")
                .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").and().exceptionHandling()
                .accessDeniedPage("/home")
                .and()
                .headers().contentTypeOptions()
                .and()
                .xssProtection()
                .and()
                .cacheControl()
                .and()
                .httpStrictTransportSecurity()
                .and()
                .frameOptions();
    }
}
