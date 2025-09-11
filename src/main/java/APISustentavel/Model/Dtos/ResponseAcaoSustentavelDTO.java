package APISustentavel.Model.Dtos;

import APISustentavel.Model.Enum.CategoriaEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAcaoSustentavelDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private CategoriaEnum categoria;
    private LocalDate dataRealizacao;
    private String responsavel;
}
