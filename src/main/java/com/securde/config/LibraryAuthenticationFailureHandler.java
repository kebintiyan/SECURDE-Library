package com.securde.config;

import com.securde.service.LoginAttemptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by patricktobias on 27/07/2017.
 */
@Component
public class LibraryAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    LoginAttemptService loginAttemptService;

    private static Logger logger = LoggerFactory.getLogger(LibraryAuthenticationFailureHandler.class);

    public LibraryAuthenticationFailureHandler() {
        super();
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String ip = request.getRemoteAddr();

        if (!loginAttemptService.isBlocked(ip)) {
            logger.warn(ip + " failed log in attempt");
            loginAttemptService.loginFailed(ip);
        }

        getRedirectStrategy().sendRedirect(request, response, "/login?error=true");

    }
}
