package cl.ucm.BackBook.backbook.repository;

import cl.ucm.BackBook.backbook.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findByType(String tipo);
    Optional<Libro> findByTitle(String titulo);


}
