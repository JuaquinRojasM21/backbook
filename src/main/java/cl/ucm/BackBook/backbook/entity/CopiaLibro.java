package cl.ucm.BackBook.backbook.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CopiaLibro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean disponible = true;

    @ManyToOne
    @JoinColumn(name = "libro_id")
    private Libro libro;
}
