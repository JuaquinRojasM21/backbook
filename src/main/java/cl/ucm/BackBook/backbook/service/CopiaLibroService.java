package cl.ucm.BackBook.backbook.service;

import cl.ucm.BackBook.backbook.entity.CopiaLibro;
import cl.ucm.BackBook.backbook.repository.CopiaLibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CopiaLibroService {

    private final CopiaLibroRepository copiaLibroRepository;

    public CopiaLibroService(CopiaLibroRepository copiaLibroRepository) {
        this.copiaLibroRepository = copiaLibroRepository;
    }

    public CopiaLibro guardar(CopiaLibro copia) {
        return copiaLibroRepository.save(copia);
    }

    public List<CopiaLibro> obtenerTodas() {
        return copiaLibroRepository.findAll();
    }
}
