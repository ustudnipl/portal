package pl.ustudni.portal.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

import javax.servlet.Filter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
public class JwtWebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    private final JwtSuccessHandler jwtSuccessHandler;
    private final JwtAuthenticationManager jwtAuthenticationManager;

    public JwtWebSecurityConfigurer(JwtSuccessHandler jwtSuccessHandler, JwtAuthenticationManager jwtAuthenticationManager) {
        this.jwtSuccessHandler = jwtSuccessHandler;
        this.jwtAuthenticationManager = jwtAuthenticationManager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/secured")
                    .authenticated()
                    .antMatchers("/non-secured")
                    .permitAll()
                    .and()
                .formLogin()
                    .successHandler(jwtSuccessHandler)
                    .permitAll()
                    .and()
                .csrf()
                    .disable()
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(STATELESS);
    }

    @Bean
    public Filter jwtAuthenticationFilter() {
        RequestHeaderAuthenticationFilter filter = new RequestHeaderAuthenticationFilter();
        filter.setPrincipalRequestHeader(HttpHeaders.AUTHORIZATION);
        filter.setAuthenticationManager(jwtAuthenticationManager);
        filter.setExceptionIfHeaderMissing(false);
        return filter;
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("admin")
                        .password("password")
                        .roles("ADMIN")
                        .build();
        return new InMemoryUserDetailsManager(user);
    }

}
