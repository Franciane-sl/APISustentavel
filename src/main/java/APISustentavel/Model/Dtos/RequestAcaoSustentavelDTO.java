package APISustentavel.Model.Dtos;

import APISustentavel.Model.Enum.CategoriaEnum;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestAcaoSustentavelDTO {

    @NotBlank(message = "O título não pode estar vazio ou conter apenas espaços em branco.")
    @Size(min = 3, max = 100, message = "O título deve ter entre 3 a 100 caracteres.")
    private String titulo;

    @NotBlank(message = "A descrição  não pode estar vazia ou conter apenas espaços em branco.")
    @Size(min = 50, max = 1000, message = "A descrição deve ter entre 50 a 1000 caracteres.")
    private String descricao;

    @NotNull(message = "A categoria não pode estar nula.")
    private CategoriaEnum categoria;

    @NotNull(message = "A data de realização não pode estar nula.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "A data de realização deve estar no presente ou no futuro.")
    private LocalDate dataRealizacao;

    @NotBlank(message = "O nome do responsável não pode estar vazio ou conter apenas espaços em branco.")
    @Size(min = 3, max = 20, message = "O nome do responsável deve ter entre 3 a 20 caracteres.")
    private String responsavel;

}
