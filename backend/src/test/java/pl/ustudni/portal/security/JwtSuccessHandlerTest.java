package pl.ustudni.portal.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class JwtSuccessHandlerTest {

    private static final String AUTH_NAME = "AUTH_NAME";
    private static final String AUTH_TOKEN = "AUTH_TOKEN";

    @Mock private JwtTokenService jwtTokenService;
    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private Authentication authentication;

    @InjectMocks private JwtSuccessHandler jwtSuccessHandler;

    @Test
    public void shouldAddTokenToHeaders() {
        doReturn(AUTH_NAME).when(authentication).getName();
        doReturn(AUTH_TOKEN).when(jwtTokenService).generateToken(AUTH_NAME);

        jwtSuccessHandler.onAuthenticationSuccess(request, response, authentication);

        verify(response).addHeader(HttpHeaders.AUTHORIZATION, AUTH_TOKEN);
    }

}