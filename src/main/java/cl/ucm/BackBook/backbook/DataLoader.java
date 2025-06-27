package cl.ucm.BackBook.backbook;

import cl.ucm.BackBook.backbook.entity.Rol;
import cl.ucm.BackBook.backbook.repository.RolRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final RolRepository rolRepository;

    public DataLoader(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    public void run(String... args) {
        if (rolRepository.count() == 0) {
            rolRepository.save(new Rol(null, "ADMIN"));
            rolRepository.save(new Rol(null, "LECTOR"));
        }
    }
}
