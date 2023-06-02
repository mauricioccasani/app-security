package pe.com.ccasani.appsecurity.security.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Slf4j
@Configuration
public class SecurityConfiguration {
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
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
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.builder()
                .username("user")
                .password(this.bCryptPasswordEncoder().encode("abc"))
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password(this.bCryptPasswordEncoder().encode("abc"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

}
