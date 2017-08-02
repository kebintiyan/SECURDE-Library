package com.securde.config;

import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;

public class WebInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext container) {
        container.addListener(new SessionListener());
        System.out.println("START");
    }
}
