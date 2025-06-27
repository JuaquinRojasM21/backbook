package cl.ucm.BackBook.backbook.service;

import cl.ucm.BackBook.backbook.entity.Libro;
import cl.ucm.BackBook.backbook.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public Libro guardarLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    public List<Libro> obtenerTodos() {
        return libroRepository.findAll();
    }
}
