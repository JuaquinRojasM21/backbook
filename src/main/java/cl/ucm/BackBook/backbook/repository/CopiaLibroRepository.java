package cl.ucm.BackBook.backbook.repository;

import cl.ucm.BackBook.backbook.entity.CopiaLibro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CopiaLibroRepository extends JpaRepository<CopiaLibro, Long> {
    List<CopiaLibro> findByLibro_Title(String titulo);

}