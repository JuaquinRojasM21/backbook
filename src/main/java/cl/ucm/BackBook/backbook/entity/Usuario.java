package cl.ucm.BackBook.backbook.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Column(unique = true)

    private String email;
    private String password;
    private Boolean estado = true;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;
}
