package pl.ustudni.portal.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import static java.util.Collections.emptyList;

@Component
public class JwtAuthenticationManager implements AuthenticationManager {

    private final JwtTokenService jwtTokenService;

    public JwtAuthenticationManager(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Object principal = authentication.getPrincipal();
        if (principal instanceof String) {
            String subject = jwtTokenService.parseToken((String) principal).getSubject();
            if (subject != null) {
                return new UsernamePasswordAuthenticationToken(subject, null, emptyList());
            }
        }
        return null;
    }

}
