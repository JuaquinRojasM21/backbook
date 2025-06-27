package cl.ucm.BackBook.backbook.controller;

import cl.ucm.BackBook.backbook.entity.Multa;
import cl.ucm.BackBook.backbook.service.MultaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/multa")
@CrossOrigin(origins = "*")
public class MultaController {

    private final MultaService multaService;

    public MultaController(MultaService multaService) {
        this.multaService = multaService;
    }

    @PostMapping("/new")
    public ResponseEntity<Multa> registrar(@RequestBody Multa multa) {
        return ResponseEntity.ok(multaService.registrar(multa));
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<Multa>> verMultas(@PathVariable Long id) {
        return ResponseEntity.ok(multaService.obtenerPorUsuario(id));
    }

    @PutMapping("/pagar/{id}")
    public ResponseEntity<Multa> pagarMulta(@PathVariable Long id) {
        return ResponseEntity.ok(multaService.marcarComoPagada(id));
    }
}
