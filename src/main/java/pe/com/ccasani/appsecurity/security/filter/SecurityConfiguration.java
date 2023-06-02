package pe.com.ccasani.appsecurity.security.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import pe.com.ccasani.appsecurity.security.athentication.CustomAuthenticationProvider;

import static org.springframework.security.config.Customizer.withDefaults;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration {
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private final CustomAuthenticationProvider authenticationProvider;

    private  AuthenticationManager authenticationManager(){
        return new ProviderManager(this.authenticationProvider);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("filterChain....");
        http.authorizeHttpRequests(
                authz -> authz.requestMatchers("/home/**").permitAll()
        );
        http.authorizeHttpRequests(authz -> authz
                        .requestMatchers("/usuarios/**").hasRole("ADMIN")
                        .requestMatchers("/clientes/**").hasRole("USER")
                        .anyRequest()
                        .authenticated())
                .httpBasic(withDefaults());
        http.addFilter(new BasicAuthenticationFilter(this.authenticationManager()));
        return http.build();
    }


}
