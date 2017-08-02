package com.securde.config;

import com.securde.export.XlsxView;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created by kevin on 6/25/2017.
 */

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter{

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return servletContext -> servletContext.getSessionCookieConfig().setName("SESSION_ID");

    }
}
