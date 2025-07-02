package cl.ucm.BackBook.backbook.service;

import cl.ucm.BackBook.backbook.entity.Usuario;
import cl.ucm.BackBook.backbook.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElseThrow();
    }

    public boolean existePorEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public Usuario cambiarEstado(String email, boolean estado) {
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow();
        usuario.setEstado(estado);
        return usuarioRepository.save(usuario);
    }
}
