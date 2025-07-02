package cl.ucm.BackBook.backbook.service;

import cl.ucm.BackBook.backbook.entity.UsuarioRol;
import cl.ucm.BackBook.backbook.repository.UsuarioRolRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioRolService {

    private final UsuarioRolRepository usuarioRolRepository;

    public UsuarioRolService(UsuarioRolRepository usuarioRolRepository) {
        this.usuarioRolRepository = usuarioRolRepository;
    }

    public UsuarioRol guardar(UsuarioRol ur) {
        return usuarioRolRepository.save(ur);
    }

    public List<UsuarioRol> obtenerTodos() {
        return usuarioRolRepository.findAll();
    }
}

