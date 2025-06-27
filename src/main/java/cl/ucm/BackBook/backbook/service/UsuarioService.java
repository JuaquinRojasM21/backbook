package cl.ucm.BackBook.backbook.service;

import cl.ucm.BackBook.backbook.dto.RegistroRequest;
import cl.ucm.BackBook.backbook.entity.Rol;
import cl.ucm.BackBook.backbook.entity.Usuario;
import cl.ucm.BackBook.backbook.repository.RolRepository;
import cl.ucm.BackBook.backbook.repository.UsuarioRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, RolRepository rolRepository){
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Usuario registrar(RegistroRequest request){
        if (usuarioRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("El correo ya está registrado.");
        }

        // Usar rol desde el request o default a LECTOR
        String nombreRol = request.getRol() != null ? request.getRol().toUpperCase() : "LECTOR";

        Rol rol = rolRepository.findByNombre(nombreRol)
                .orElseThrow(() -> new RuntimeException("Rol " + nombreRol + " no encontrado"));

        Usuario nuevo = new Usuario();
        nuevo.setNombre(request.getNombre());
        nuevo.setEmail(request.getEmail());
        nuevo.setPassword(passwordEncoder.encode(request.getPassword()));
        nuevo.setEstado(true);
        nuevo.setRol(rol);

        return usuarioRepository.save(nuevo);
    }
}
