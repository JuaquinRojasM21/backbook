package cl.ucm.BackBook.backbook.service;

import cl.ucm.BackBook.backbook.entity.*;
import cl.ucm.BackBook.backbook.repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CopiaLibroRepository copiaLibroRepository;
    private final MultaRepository multaRepository;

    public PrestamoService(
        PrestamoRepository repo,
        UsuarioRepository usuarioRepo,
        CopiaLibroRepository copiaRepo,
        MultaRepository multaRepo
    ) {
        this.prestamoRepository = repo;
        this.usuarioRepository = usuarioRepo;
        this.copiaLibroRepository = copiaRepo;
        this.multaRepository = multaRepo;
    }

    public Prestamo guardar(Prestamo p) {
        Usuario usuario = usuarioRepository.findById(p.getUsuario().getId())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        CopiaLibro copia = copiaLibroRepository.findById(p.getCopiaLibro().getId())
            .orElseThrow(() -> new RuntimeException("Copia no encontrada"));

        if (!usuario.getEstado()) throw new RuntimeException("El lector está bloqueado");
        if (!copia.getEstado()) throw new RuntimeException("Copia no disponible");

        p.setFechaReserva(LocalDate.now());
        p.setFechaDevolucion(LocalDate.now().plusDays(5));
        p.setEstado(true);

        copia.setEstado(false);
        copiaLibroRepository.save(copia);

        return prestamoRepository.save(p);
    }

    public List<Prestamo> obtenerTodos() {
        return prestamoRepository.findAll();
    }

    public List<Prestamo> porEmail(String email) {
        return prestamoRepository.findByUsuarioEmail(email);
    }

    public ResponseEntity<Prestamo> marcarComoDevuelto(Long id, Prestamo body) {
        Prestamo p = prestamoRepository.findById(id).orElseThrow();
        CopiaLibro copia = copiaLibroRepository.findById(p.getCopiaLibro().getId()).orElseThrow();
        Usuario usuario = usuarioRepository.findById(p.getUsuario().getId()).orElseThrow();

        p.setEstado(false);
        copia.setEstado(true);

        if (LocalDate.now().isAfter(p.getFechaDevolucion())) {
            long diasAtraso = ChronoUnit.DAYS.between(p.getFechaDevolucion(), LocalDate.now());
            double monto = diasAtraso * 1000;

            usuario.setEstado(false);
            multaRepository.save(new Multa(null, monto, "Multa por libro: " + copia.getLibro().getTitle(), true, usuario));
        }

        copiaLibroRepository.save(copia);
        usuarioRepository.save(usuario);
        prestamoRepository.save(p);

        return ResponseEntity.ok(p);
    }
}


