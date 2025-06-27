package cl.ucm.BackBook.backbook.service;

import cl.ucm.BackBook.backbook.entity.CopiaLibro;
import cl.ucm.BackBook.backbook.entity.Prestamo;
import cl.ucm.BackBook.backbook.repository.CopiaLibroRepository;
import cl.ucm.BackBook.backbook.repository.PrestamoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final CopiaLibroRepository copiaLibroRepository;

    public PrestamoService(PrestamoRepository prestamoRepository, CopiaLibroRepository copiaLibroRepository) {
        this.prestamoRepository = prestamoRepository;
        this.copiaLibroRepository = copiaLibroRepository;
    }

    public Prestamo registrar(Prestamo prestamo) {
        CopiaLibro copia = prestamo.getCopiaLibro();

        // Validar disponibilidad
        if (!copia.getDisponible()) {
            throw new RuntimeException("La copia ya está prestada");
        }

        copia.setDisponible(false); // Marcar como no disponible
        copiaLibroRepository.save(copia);

        return prestamoRepository.save(prestamo);
    }

    public List<Prestamo> obtenerTodos() {
        return prestamoRepository.findAll();
    }
    public List<Prestamo> obtenerPorUsuario(Long idUsuario) {
    return prestamoRepository.findByUsuarioId(idUsuario);
    }


    public Prestamo devolver(Long id) {
        Prestamo prestamo = prestamoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado"));

        if (prestamo.getDevuelto()) {
            throw new RuntimeException("El libro ya fue devuelto");
        }

        prestamo.setDevuelto(true);
        prestamo.getCopiaLibro().setDisponible(true);
        copiaLibroRepository.save(prestamo.getCopiaLibro());
        return prestamoRepository.save(prestamo);
    }
}
