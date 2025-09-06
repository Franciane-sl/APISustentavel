package APISustentavel.Model.Entity;

import APISustentavel.Model.Enum.CategoriaEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "acaoSustentavel")
public class AcaoSustentavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String titulo;
    @Column(length = 1000)
    private String descricao;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoriaEnum categoria;
    @Column(nullable = false)
    private LocalDate dataRealizacao;
    @Column(nullable = false)
    private String responsavel;
}
