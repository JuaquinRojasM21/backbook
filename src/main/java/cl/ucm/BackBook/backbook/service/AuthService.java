package cl.ucm.BackBook.backbook.service;

import cl.ucm.BackBook.backbook.config.JwtUtil;
import cl.ucm.BackBook.backbook.dto.LoginRequest;
import cl.ucm.BackBook.backbook.entity.Usuario;
import cl.ucm.BackBook.backbook.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(UsuarioRepository usuarioRepository, JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public String login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!usuario.getEstado()) {
            throw new RuntimeException("El usuario está inactivo");
        }

        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return jwtUtil.generateToken(usuario.getEmail(), usuario.getRol().getNombre());
    }

    public String codificarClave(String clave) {
        return passwordEncoder.encode(clave);
    }
}

