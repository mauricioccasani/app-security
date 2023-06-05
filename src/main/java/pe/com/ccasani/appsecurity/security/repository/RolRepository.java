package pe.com.ccasani.appsecurity.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.ccasani.appsecurity.security.entity.RolEntity;

public interface RolRepository extends JpaRepository<RolEntity, Long> {
}
