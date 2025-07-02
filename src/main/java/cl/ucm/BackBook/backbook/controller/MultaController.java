package cl.ucm.BackBook.backbook.controller;

import cl.ucm.BackBook.backbook.entity.Multa;
import cl.ucm.BackBook.backbook.service.MultaService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fine")
@CrossOrigin(origins = "*")
public class MultaController {

    private final MultaService multaService;

    public MultaController(MultaService multaService) {
        this.multaService = multaService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'LECTOR')")
    @GetMapping("/find/{email}")
    public ResponseEntity<List<Multa>> multasPorEmail(@PathVariable String email) {
        return ResponseEntity.ok(multaService.porEmail(email));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/new")
    public ResponseEntity<Multa> crear(@RequestBody Multa multa) {
        return ResponseEntity.ok(multaService.guardar(multa));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/pagar/{id}")
    public ResponseEntity<Multa> pagar(@PathVariable Long id) {
        return ResponseEntity.ok(multaService.marcarComoPagada(id));
    }
}


