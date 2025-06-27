package cl.ucm.BackBook.backbook.controller;

import cl.ucm.BackBook.backbook.entity.CopiaLibro;
import cl.ucm.BackBook.backbook.service.CopiaLibroService;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/new")
    public ResponseEntity<CopiaLibro> crearCopia(@RequestBody CopiaLibro copia) {
        return ResponseEntity.ok(copiaLibroService.guardar(copia));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CopiaLibro>> obtenerCopias() {
        return ResponseEntity.ok(copiaLibroService.obtenerTodas());
    }
}
