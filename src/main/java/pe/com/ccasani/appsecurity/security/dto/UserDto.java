package pe.com.ccasani.appsecurity.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class UserDto {
    private String name;
    private String password;
    private List<GrantedAuthority> authorities;
}
