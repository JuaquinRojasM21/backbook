package cl.ucm.BackBook.backbook.service;

import cl.ucm.BackBook.backbook.entity.Multa;
import cl.ucm.BackBook.backbook.repository.MultaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MultaService {

    private final MultaRepository multaRepository;

    public MultaService(MultaRepository multaRepository) {
        this.multaRepository = multaRepository;
    }

    public Multa registrar(Multa multa) {
        return multaRepository.save(multa);
    }

    public List<Multa> obtenerPorUsuario(Long idUsuario) {
        return multaRepository.findByUsuarioId(idUsuario);
    }

    public Multa marcarComoPagada(Long id) {
        Multa multa = multaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Multa no encontrada"));
        multa.setPagada(true);
        return multaRepository.save(multa);
    }
}
