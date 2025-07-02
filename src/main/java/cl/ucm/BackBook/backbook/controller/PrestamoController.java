package cl.ucm.BackBook.backbook.controller;

import cl.ucm.BackBook.backbook.entity.Prestamo;
import cl.ucm.BackBook.backbook.service.PrestamoService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
@CrossOrigin(origins = "*")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'LECTOR')")
    @GetMapping("/find/{email}")
    public ResponseEntity<List<Prestamo>> porEmail(@PathVariable String email) {
        return ResponseEntity.ok(prestamoService.porEmail(email));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/new")
    public ResponseEntity<Prestamo> crear(@RequestBody Prestamo prestamo) {
        return ResponseEntity.ok(prestamoService.guardar(prestamo));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/return/{id}")
    public ResponseEntity<Prestamo> devolver(@PathVariable Long id, @RequestBody Prestamo body) {
        return prestamoService.marcarComoDevuelto(id, body);
    }
}


