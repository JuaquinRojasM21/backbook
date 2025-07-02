package cl.ucm.BackBook.backbook.service;

import cl.ucm.BackBook.backbook.entity.CopiaLibro;
import cl.ucm.BackBook.backbook.repository.CopiaLibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CopiaLibroService {
    private final CopiaLibroRepository copiaLibroRepository;

    public CopiaLibroService(CopiaLibroRepository repo) {
        this.copiaLibroRepository = repo;
    }

    public CopiaLibro guardar(CopiaLibro copia) {
        if (copia.getEstado() == null) copia.setEstado(true);
        return copiaLibroRepository.save(copia);
    }

    public List<CopiaLibro> obtenerTodos() {
        return copiaLibroRepository.findAll();
    }

    public List<CopiaLibro> buscarPorTituloLibro(String titulo) {
        return copiaLibroRepository.findByLibro_Title(titulo);
    }

    public Optional<CopiaLibro> buscarPorId(Long id) {
        return copiaLibroRepository.findById(id);
    }
}

