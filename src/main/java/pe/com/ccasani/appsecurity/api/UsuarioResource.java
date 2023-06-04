package pe.com.ccasani.appsecurity.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {

    @GetMapping
    public ResponseEntity<String> test() {
        return ResponseEntity.ok().body("user");
    }
}
