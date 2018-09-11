package pl.ustudni.portal.security;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtTokenService jwtTokenService;

    public JwtSuccessHandler(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication auth) {
        resp.addHeader(HttpHeaders.AUTHORIZATION, jwtTokenService.generateToken(auth.getName()));
    }

}
