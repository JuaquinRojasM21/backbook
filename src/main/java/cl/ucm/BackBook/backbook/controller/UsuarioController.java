package cl.ucm.BackBook.backbook.controller;

import cl.ucm.BackBook.backbook.entity.Usuario;
import cl.ucm.BackBook.backbook.service.UsuarioService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reader")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/find/{email}")
    public ResponseEntity<Usuario> buscarPorEmail(@PathVariable String email) {
        return ResponseEntity.ok(usuarioService.buscarPorEmail(email));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/state/{email}")
    public ResponseEntity<Usuario> cambiarEstado(@PathVariable String email, @RequestBody boolean estado) {
        return ResponseEntity.ok(usuarioService.cambiarEstado(email, estado));
    }
}
