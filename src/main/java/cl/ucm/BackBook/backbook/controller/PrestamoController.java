package cl.ucm.BackBook.backbook.controller;

import cl.ucm.BackBook.backbook.entity.Prestamo;
import cl.ucm.BackBook.backbook.service.PrestamoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestamo")
@CrossOrigin(origins = "*")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @PostMapping("/new")
    public ResponseEntity<Prestamo> registrarPrestamo(@RequestBody Prestamo prestamo) {
        return ResponseEntity.ok(prestamoService.registrar(prestamo));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Prestamo>> obtenerTodos() {
        return ResponseEntity.ok(prestamoService.obtenerTodos());
    }

    @PutMapping("/devolver/{id}")
    public ResponseEntity<Prestamo> devolverLibro(@PathVariable Long id) {
        return ResponseEntity.ok(prestamoService.devolver(id));
    }
    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<Prestamo>> obtenerPorUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(prestamoService.obtenerPorUsuario(id));
    }

}
