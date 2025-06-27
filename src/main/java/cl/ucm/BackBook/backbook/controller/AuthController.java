package cl.ucm.BackBook.backbook.controller;

import cl.ucm.BackBook.backbook.dto.LoginRequest;
import cl.ucm.BackBook.backbook.dto.RegistroRequest;
import cl.ucm.BackBook.backbook.entity.Usuario;
import cl.ucm.BackBook.backbook.service.AuthService;
import cl.ucm.BackBook.backbook.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UsuarioService usuarioService;
    private final AuthService authService; 

    public AuthController(UsuarioService usuarioService, AuthService authService) {
        this.usuarioService = usuarioService;
        this.authService = authService; 
    }

    @PostMapping("/register")
    public ResponseEntity<Usuario> registrar(@RequestBody RegistroRequest request) {
        Usuario nuevo = usuarioService.registrar(request);
        return ResponseEntity.ok(nuevo);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        String token = authService.login(request);
        return ResponseEntity.ok(token);
    }
}