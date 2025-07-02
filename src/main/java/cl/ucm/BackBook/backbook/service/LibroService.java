package cl.ucm.BackBook.backbook.service;

import cl.ucm.BackBook.backbook.entity.Libro;
import cl.ucm.BackBook.backbook.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {
    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public Libro guardar(Libro libro) {
        return libroRepository.save(libro);
    }

    public List<Libro> obtenerTodos() {
        return libroRepository.findAll();
    }

    public List<Libro> buscarPorTipo(String tipo) {
        return libroRepository.findByType(tipo);
    }

    public Optional<Libro> buscarPorTitulo(String titulo) {
        return libroRepository.findByTitle(titulo);
    }
}


