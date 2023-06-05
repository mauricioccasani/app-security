package pe.com.ccasani.appsecurity.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.ccasani.appsecurity.security.entity.UsuarioEntity;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity>findByUsuario(String usuario);
}
