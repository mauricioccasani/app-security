package pe.com.ccasani.appsecurity.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pe.com.ccasani.appsecurity.security.dto.response.GenericResponse;

@RestControllerAdvice
public class SecurityExceptionHandler {
    public static final String INTENTAR_MAS_TARDE = "Lo sentimos por favor vuelva intentar mas tarde.";

    @ExceptionHandler
    public ResponseEntity<GenericResponse> tareaException(SecurityException exs) {
        boolean exiteError = StringUtils.hasText(exs.getMessage());
        if (exiteError) {
            return ResponseEntity.status(exs.getStatus()).body(new GenericResponse("999",exs.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GenericResponse("999",INTENTAR_MAS_TARDE));
    }
}
