package pe.com.ccasani.appsecurity.security.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import pe.com.ccasani.appsecurity.security.athentication.CustomAuthenticationEntryPoint;
import pe.com.ccasani.appsecurity.security.service.UserDetailsServiceImpl;

import static org.springframework.security.config.Customizer.withDefaults;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class SecurityFilter {


    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserDetailsServiceImpl userDetailsService;

    private final CustomAuthenticationEntryPoint authEntryPoint;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("filterChain....");
        http.authorizeHttpRequests(
                authz -> authz.requestMatchers("/home/**").permitAll()
        );
        http.authorizeHttpRequests(authz -> authz
                        .requestMatchers("/usuarios/**").hasRole("USER")
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest()
                        .authenticated())
                .httpBasic(withDefaults()
                )
                .exceptionHandling(ex -> ex.authenticationEntryPoint(this.authEntryPoint));
        http.authenticationProvider(this.daoAuthenticationProvider());
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(this.userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(this.bCryptPasswordEncoder);
        return daoAuthenticationProvider;
    }

}
