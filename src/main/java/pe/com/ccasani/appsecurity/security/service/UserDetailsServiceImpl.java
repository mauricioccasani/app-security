package pe.com.ccasani.appsecurity.security.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.com.ccasani.appsecurity.security.dto.UserDto;
import pe.com.ccasani.appsecurity.security.exception.SecurityException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    private static List<GrantedAuthority> authorities(String... roles) {

        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String rol : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + rol));
        }
        return authorities;
    }


    public Optional<UserDto> getByName(String name) {
        return this.getUsers().stream().filter(u -> u.getName().equals(name)).findFirst();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        Optional<UserDto> customUser = getByName(username);

        if (!customUser.isPresent()) {
            throw new SecurityException("Usuario no encontrado!", HttpStatus.UNAUTHORIZED);
        }

        return User.builder().username(customUser.get().getName()).password(customUser.get().getPassword()).authorities(customUser.get().getAuthorities()).build();


    }

    private List<UserDto> getUsers() {
        return List.of(UserDto.builder().name("user").password("123").authorities(authorities("USER")).build(),
                UserDto.builder().name("admin").password("123").authorities(authorities("ADMIN", "USER")).build(),
                UserDto.builder().name("super").password("123").authorities(authorities("ADMIN", "USER")).build());
    }

}