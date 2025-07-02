package cl.ucm.BackBook.backbook.repository;

import cl.ucm.BackBook.backbook.entity.Multa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MultaRepository extends JpaRepository<Multa, Long> {
    java.util.List<Multa> findByUsuarioId(Long id);
    List<Multa> findByUsuarioEmail(String email);


}
