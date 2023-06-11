package pe.com.ccasani.appsecurity.security.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import pe.com.ccasani.appsecurity.security.entity.Rol;
import pe.com.ccasani.appsecurity.security.entity.UsuarioEntity;
import pe.com.ccasani.appsecurity.security.exception.SecurityException;
import pe.com.ccasani.appsecurity.security.repository.UsuarioRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplTest {
    @Mock
    UsuarioRepository usuarioRepository;
    @InjectMocks
    UserDetailsServiceImpl userDetailsService;

    @Test
    void loadUserByUsername() {
        UsuarioEntity usuarioEntity = UsuarioEntity.builder().id(1L).nombre("Mauricio")
                .usuario("mau").clave("123")
                .rol(Rol.ROLE_USER).build();
        given(this.usuarioRepository.findByUsuario(any())).willReturn(Optional.of(usuarioEntity));
        UserDetails userDetails = this.userDetailsService.loadUserByUsername("mau");
        Assertions.assertEquals("mau", userDetails.getUsername());
    }

    @Test
    void loadUserByUsernameNotFound() {
        UsuarioEntity usuarioEntity = UsuarioEntity.builder().id(1L).nombre("Mauricio")
                .usuario("mau").clave("123")
                .rol(Rol.ROLE_USER).build();
        given(this.usuarioRepository.findByUsuario(any())).willReturn(Optional.empty());
        assertThrows(SecurityException.class, () -> {
            this.userDetailsService.loadUserByUsername("mauu");
        });

    }
}