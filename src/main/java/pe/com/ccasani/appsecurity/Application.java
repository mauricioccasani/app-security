package pe.com.ccasani.appsecurity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pe.com.ccasani.appsecurity.security.entity.Rol;
import pe.com.ccasani.appsecurity.security.entity.UsuarioEntity;
import pe.com.ccasani.appsecurity.security.repository.UsuarioRepository;
@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class Application implements CommandLineRunner {

    public static final String CLAVE = "$2a$10$1f.oHaM0Vt2hmVI4ParKYeovNKZ2Faiwy0XEqSv6ramKFP4BOYol6";
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UsuarioRepository usuarioRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
		this.usuarioRepository.save(UsuarioEntity.builder().usuario("user").clave(CLAVE).nombre("Mauricio").estado("A").rol(Rol.ROLE_USER).build());
		this.usuarioRepository.save(UsuarioEntity.builder().usuario("admin").clave(CLAVE).nombre("Fabricio").estado("A").rol(Rol.ROLE_ADMIN).build());
		this.usuarioRepository.save(UsuarioEntity.builder().usuario("super").clave(CLAVE).nombre("Ccasani").estado("A").rol(Rol.ROLE_SUPER).build());
   		log.info("Listar: {}",this.usuarioRepository.findAll());
    }
}
