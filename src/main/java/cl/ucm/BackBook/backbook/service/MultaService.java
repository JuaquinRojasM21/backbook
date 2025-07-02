package cl.ucm.BackBook.backbook.service;

import cl.ucm.BackBook.backbook.entity.Multa;
import cl.ucm.BackBook.backbook.entity.Usuario;
import cl.ucm.BackBook.backbook.repository.MultaRepository;
import cl.ucm.BackBook.backbook.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MultaService {
    private final MultaRepository multaRepository;
    private final UsuarioRepository usuarioRepository;

    public MultaService(MultaRepository multaRepository, UsuarioRepository usuarioRepository) {
        this.multaRepository = multaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Multa guardar(Multa multa) {
        return multaRepository.save(multa);
    }

    public List<Multa> porEmail(String email) {
        return multaRepository.findByUsuarioEmail(email);
    }

    public Multa marcarComoPagada(Long id) {
        Multa m = multaRepository.findById(id).orElseThrow();
        m.setEstado(false);

        Usuario u = m.getUsuario();
        u.setEstado(true);
        usuarioRepository.save(u);

        return multaRepository.save(m);
    }
}

