package pe.com.ccasani.appsecurity.security.athentication;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        log.info(String.format("username %s", username));
        log.info("password {}", password);

        UserDetails userDetails = isValidUser(username, password);

        if (userDetails != null) {
            return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
        } else {
            throw new BadCredentialsException("Usuarion incorrecto");
        }
    }

    @Override
    public boolean supports(Class<?> authenticationType) {
        return authenticationType.equals(UsernamePasswordAuthenticationToken.class);
    }

    private UserDetails isValidUser(String username, String password) {

        if (username.equalsIgnoreCase("user") && password.equals("abc")) {
            log.info("User");
            UserDetails user = User.withUsername(username).password("abc").roles("USER").build();
            return user;
        }
        if (username.equalsIgnoreCase("admin") && password.equals("abc")) {
            log.info("Admin");
            UserDetails user = User.withUsername(username).password("abc").roles("ADMIN").build();
            return user;
        }

        return null;
    }
}