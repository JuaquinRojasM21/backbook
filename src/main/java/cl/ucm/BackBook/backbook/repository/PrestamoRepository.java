package cl.ucm.BackBook.backbook.repository;

import cl.ucm.BackBook.backbook.entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;  
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    List<Prestamo> findByUsuarioId(Long usuarioId);
}
