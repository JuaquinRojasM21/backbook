package cl.ucm.BackBook.backbook.controller;

import cl.ucm.BackBook.backbook.entity.CopiaLibro;
import cl.ucm.BackBook.backbook.service.CopiaLibroService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/copia")
@CrossOrigin(origins = "*")
public class CopiaLibroController {

    private final CopiaLibroService copiaLibroService;

    public CopiaLibroController(CopiaLibroService copiaLibroService) {
        this.copiaLibroService = copiaLibroService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/new")
    public ResponseEntity<CopiaLibro> crear(@RequestBody CopiaLibro copia) {
        return ResponseEntity.ok(copiaLibroService.guardar(copia));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<CopiaLibro>> obtenerTodas() {
        return ResponseEntity.ok(copiaLibroService.obtenerTodos());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/bytitle/{title}")
    public ResponseEntity<List<CopiaLibro>> porTitulo(@PathVariable String title) {
        return ResponseEntity.ok(copiaLibroService.buscarPorTituloLibro(title));
    }
}
