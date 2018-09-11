package pl.ustudni.portal.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class JwtTokenServiceTest {

    private final long YESTERDAY = -24*60*60;
    private final long TOMORROW = 24*60*60;
    private final String SECRET_1 = "1_SECRET";
    private final String SECRET_2 = "2_SECRET";
    private final String SUBJECT = "SUBJECT";

    @Mock private JwtProperties jwtProperties;
    @InjectMocks private JwtTokenService jwtTokenService;

    @Test
    public void shouldDecodeEncodedSubject() {
        doReturn(TOMORROW).when(jwtProperties).getExpirationTime();
        doReturn(SECRET_1).when(jwtProperties).getSecret();

        String token = jwtTokenService.generateToken(SUBJECT);
        assertThat(jwtTokenService.parseToken(token).getSubject()).isEqualTo(SUBJECT);
    }

    @Test
    public void shouldThrowExceptionWhenTokenExpired() {
        doReturn(YESTERDAY).when(jwtProperties).getExpirationTime();
        doReturn(SECRET_1).when(jwtProperties).getSecret();

        String token = jwtTokenService.generateToken(SUBJECT);
        assertThat(jwtTokenService.parseToken(token)).isNull();
    }

    @Test
    public void shouldThrowExceptionWhenWrongSecretProvided() {
        doReturn(TOMORROW).when(jwtProperties).getExpirationTime();
        doReturn(SECRET_1).when(jwtProperties).getSecret();

        String token = jwtTokenService.generateToken(SUBJECT);

        doReturn(SECRET_2).when(jwtProperties).getSecret();

        assertThat(jwtTokenService.parseToken(token)).isNull();
    }

}