package cl.ucm.BackBook.backbook.controller;

import cl.ucm.BackBook.backbook.dto.LoginRequest;
import cl.ucm.BackBook.backbook.dto.RegistroAdminRequest;
import cl.ucm.BackBook.backbook.dto.RegistroRequest;
import cl.ucm.BackBook.backbook.entity.Rol;
import cl.ucm.BackBook.backbook.entity.Usuario;
import cl.ucm.BackBook.backbook.service.AuthService;
import cl.ucm.BackBook.backbook.service.RolService;
import cl.ucm.BackBook.backbook.service.UsuarioService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;
    private final UsuarioService usuarioService;
    private final RolService rolService;

    public AuthController(AuthService authService, UsuarioService usuarioService, RolService rolService) {
        this.authService = authService;
        this.usuarioService = usuarioService;
        this.rolService = rolService;
    }
    //endpoint para registrar el admin (uso temporal)
    @PostMapping("/register-admin")
    public ResponseEntity<?> registrarAdmin(@RequestBody RegistroAdminRequest request) {
    if (usuarioService.existePorEmail(request.getEmail())) {
        return ResponseEntity.badRequest().body("El correo ya está registrado.");
    }

    Rol rolAdmin = rolService.buscarPorNombre("ADMIN")
            .orElseThrow(() -> new RuntimeException("Rol ADMIN no encontrado"));

    Usuario nuevo = new Usuario();
    nuevo.setNombre(request.getNombre());
    nuevo.setApellido(request.getApellido());
    nuevo.setEmail(request.getEmail());
    nuevo.setPassword(authService.codificarClave(request.getPassword()));
    nuevo.setEstado(true);
    nuevo.setRol(rolAdmin);

    usuarioService.guardar(nuevo);
    return ResponseEntity.ok("Administrador creado correctamente");
    }

    @PostMapping("/register")
    public ResponseEntity<?> registrar(@RequestBody RegistroRequest request) {
        if (usuarioService.existePorEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body("El correo ya está registrado.");
        }

        Rol rol = rolService.buscarPorNombre("LECTOR")
                .orElseThrow(() -> new RuntimeException("Rol LECTOR no encontrado"));

        Usuario nuevo = new Usuario();
        nuevo.setNombre(request.getNombre());
        nuevo.setEmail(request.getEmail());
        nuevo.setPassword(authService.codificarClave(request.getPassword()));
        nuevo.setEstado(true);
        nuevo.setRol(rol);

        usuarioService.guardar(nuevo);
        return ResponseEntity.ok("Usuario registrado correctamente");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            String token = authService.login(request);
            return ResponseEntity.ok(token);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}
