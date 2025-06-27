package cl.ucm.BackBook.backbook.controller;

import cl.ucm.BackBook.backbook.entity.Libro;
import cl.ucm.BackBook.backbook.service.LibroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
@CrossOrigin(origins = "*")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @PostMapping("/new")
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro) {
        Libro nuevo = libroService.guardarLibro(libro);
        return ResponseEntity.ok(nuevo);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Libro>> obtenerLibros() {
        return ResponseEntity.ok(libroService.obtenerTodos());
    }
}
