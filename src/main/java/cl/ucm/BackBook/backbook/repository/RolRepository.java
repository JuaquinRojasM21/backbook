package cl.ucm.BackBook.backbook.repository;

import cl.ucm.BackBook.backbook.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

//import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {
    java.util.Optional<Rol> findByNombre(String nombre);
}
