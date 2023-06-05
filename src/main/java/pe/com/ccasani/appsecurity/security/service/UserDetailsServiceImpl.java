package pe.com.ccasani.appsecurity.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.com.ccasani.appsecurity.security.entity.UsuarioEntity;
import pe.com.ccasani.appsecurity.security.exception.SecurityException;
import pe.com.ccasani.appsecurity.security.repository.UsuarioRepository;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        Optional<UsuarioEntity> usuario = this.usuarioRepository.findByUsuario(username);

        if (!usuario.isPresent()) {
            throw new SecurityException("Usuario no encontrado!", HttpStatus.UNAUTHORIZED);
        }

        return User.builder()
                .username(usuario.get().getUsuario())
                .password(usuario.get().getClave())
                .authorities(usuario.get().getRol().name())
                .build();
    }

}