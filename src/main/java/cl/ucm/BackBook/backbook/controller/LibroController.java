package cl.ucm.BackBook.backbook.controller;

import cl.ucm.BackBook.backbook.entity.Libro;
import cl.ucm.BackBook.backbook.entity.CopiaLibro;
import cl.ucm.BackBook.backbook.service.LibroService;
import cl.ucm.BackBook.backbook.service.CopiaLibroService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
@CrossOrigin(origins = "*")
public class LibroController {

    private final LibroService libroService;
    private final CopiaLibroService copiaLibroService;

    public LibroController(LibroService libroService, CopiaLibroService copiaLibroService) {
        this.libroService = libroService;
        this.copiaLibroService = copiaLibroService;
    }

    // === PÚBLICO ===
    @GetMapping("/all")
    public ResponseEntity<List<Libro>> obtenerTodos() {
        return ResponseEntity.ok(libroService.obtenerTodos());
    }

    @GetMapping("/all/{type}")
    public ResponseEntity<List<Libro>> porTipo(@PathVariable String type) {
        return ResponseEntity.ok(libroService.buscarPorTipo(type));
    }

    // === ADMIN ===
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/new")
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro) {
        return ResponseEntity.ok(libroService.guardar(libro));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/newcopy")
    public ResponseEntity<CopiaLibro> nuevaCopia(@RequestBody CopiaLibro copia) {
        return ResponseEntity.ok(copiaLibroService.guardar(copia));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/find/{title}")
    public ResponseEntity<Libro> buscarPorTitulo(@PathVariable String title) {
        return libroService.buscarPorTitulo(title)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/copy/{title}")
    public ResponseEntity<List<CopiaLibro>> copiasPorLibro(@PathVariable String title) {
        return ResponseEntity.ok(copiaLibroService.buscarPorTituloLibro(title));
    }
}

