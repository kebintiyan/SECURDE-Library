package com.securde.config;

import com.securde.model.account.Role;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.method.P;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

/**
 * Created by kevin on 6/26/2017.
 */
@Component
public class LibraryAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    protected Log logger = LogFactory.getLog(this.getClass());

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
    }

    protected void handle(HttpServletRequest request,
                          HttpServletResponse response, Authentication authentication)
            throws IOException {

        String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            logger.debug(
                    "Response has already been committed. Unable to redirect to "
                            + targetUrl);
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(Authentication authentication) {

        Collection<? extends GrantedAuthority> authorities
                = authentication.getAuthorities();

        for (GrantedAuthority grantedAuthority : authorities) {
            String auth = grantedAuthority.getAuthority();
            if (auth.equals(Role.ADMINISTRATOR.getAuthority())) {
                return "/admin/home";
            }
            else if (auth.equals(Role.MANAGER.getAuthority())) {
                return "/manager/home";
            }
            else if (auth.equals(Role.STAFF.getAuthority())) {
                return "/staff/home";
            }
            else if (auth.equals(Role.STUDENT.getAuthority()) ||
                    auth.equals(Role.FACULTY.getAuthority())) {
                return "/";
            }
        }

        throw new IllegalStateException();
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

}
