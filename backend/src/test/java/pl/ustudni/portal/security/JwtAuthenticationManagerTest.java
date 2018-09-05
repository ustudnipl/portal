package pl.ustudni.portal.security;

import io.jsonwebtoken.Claims;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class JwtAuthenticationManagerTest {

    private static final String AUTH_NAME = "AUTH_NAME";
    private static final String AUTH_TOKEN = "AUTH_TOKEN";

    @Mock private JwtTokenService jwtTokenService;
    @Mock private Authentication authentication;
    @Mock private Claims claims;

    @InjectMocks JwtAuthenticationManager jwtAuthenticationManager;

    @Test
    public void shouldReturnNullWhenPrincipalIsNull() {
        doReturn(null).when(authentication).getPrincipal();

        assertThat(jwtAuthenticationManager.authenticate(authentication)).isNull();
    }

    @Test
    public void shouldReturnNullWhenPrincipalIsNotString() {
        doReturn(new Object()).when(authentication).getPrincipal();

        assertThat(jwtAuthenticationManager.authenticate(authentication)).isNull();
    }

    @Test
    public void shouldReturnNullWhenTokenHasNoSubject() {
        doReturn(AUTH_TOKEN).when(authentication).getPrincipal();
        doReturn(claims).when(jwtTokenService).parseToken(AUTH_TOKEN);
        doReturn(null).when(claims).getSubject();

        assertThat(jwtAuthenticationManager.authenticate(authentication)).isNull();
    }

    @Test
    public void shouldReturnAuthenticationWhenTokenHasSubject() {
        doReturn(AUTH_TOKEN).when(authentication).getPrincipal();
        doReturn(claims).when(jwtTokenService).parseToken(AUTH_TOKEN);
        doReturn(AUTH_NAME).when(claims).getSubject();

        assertThat(jwtAuthenticationManager.authenticate(authentication)).isEqualTo(
                new UsernamePasswordAuthenticationToken(AUTH_NAME, null, emptyList())
        );
    }

}