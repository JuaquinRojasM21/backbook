package cl.ucm.BackBook.backbook.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Multa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private LocalDate fecha;
    private Boolean pagada = false;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
