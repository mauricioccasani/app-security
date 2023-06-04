package pe.com.ccasani.appsecurity.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminResource {
    @GetMapping
    public ResponseEntity<String> getAdmin() {
        return ResponseEntity.ok().body("admin");
    }
}
