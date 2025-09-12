package APISustentavel.Controller;

import APISustentavel.Model.Dtos.RequestAcaoSustentavelDTO;
import APISustentavel.Model.Dtos.ResponseAcaoSustentavelDTO;
import APISustentavel.Model.Entity.AcaoSustentavel;
import APISustentavel.Model.Enum.CategoriaEnum;
import APISustentavel.Model.Exceptions.RecursoNaoEncontradoException;
import APISustentavel.Service.AcaoSustentavelService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/acoes")
public class AcaoSustentavelController {

    @Autowired
    private AcaoSustentavelService acaoSustentavelService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ResponseAcaoSustentavelDTO>> list(){
        List<ResponseAcaoSustentavelDTO> acoesSustentaveis = this.acaoSustentavelService.findAllAcoesSustentaveis().stream()
                .map(acaoSustentavel -> modelMapper.map(acaoSustentavel, ResponseAcaoSustentavelDTO.class)).collect(Collectors.toList());

        return acoesSustentaveis.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(acoesSustentaveis);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAcaoSustentavelDTO> getById(@PathVariable Long id){
        AcaoSustentavel acaoSustentavel = acaoSustentavelService.findAcaoSustentavelById(id);
        ResponseAcaoSustentavelDTO acaoSustentavelDTO = modelMapper.map(acaoSustentavel, ResponseAcaoSustentavelDTO.class);

        return ResponseEntity.ok(acaoSustentavelDTO);
    }

    @GetMapping("/categoria")
    public ResponseEntity<List<ResponseAcaoSustentavelDTO>> getByCategoria(@RequestParam("tipo") String tipo) {
        try {

            var categoria = CategoriaEnum.valueOf(tipo.toUpperCase());

            List<ResponseAcaoSustentavelDTO> acoes = acaoSustentavelService.findByCategoria(categoria)
                    .stream()
                    .map(acao -> modelMapper.map(acao, ResponseAcaoSustentavelDTO.class))
                    .collect(Collectors.toList());

            return acoes.isEmpty()
                    ? ResponseEntity.noContent().build()
                    : ResponseEntity.ok(acoes);

        } catch (IllegalArgumentException e) {
            throw new RecursoNaoEncontradoException("Categoria inválida: " + tipo);
        }
    }

    @PostMapping
    public ResponseEntity<ResponseAcaoSustentavelDTO> create(@RequestBody @Valid RequestAcaoSustentavelDTO acaoSustentavelDTO) throws Exception{
        AcaoSustentavel acaoSustentavel = modelMapper.map(acaoSustentavelDTO, AcaoSustentavel.class);
        AcaoSustentavel createdAcaoSustentavel = acaoSustentavelService.create(acaoSustentavel);
        ResponseAcaoSustentavelDTO createdAcaoSustentavelDTO = modelMapper.map(createdAcaoSustentavel, ResponseAcaoSustentavelDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdAcaoSustentavelDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseAcaoSustentavelDTO> update(@PathVariable Long id, @RequestBody RequestAcaoSustentavelDTO acaoSustentavelDTO) throws Exception{
        AcaoSustentavel acaoSustentavel = modelMapper.map(acaoSustentavelDTO, AcaoSustentavel.class);
        AcaoSustentavel acaoSustentavelUpdate = this.acaoSustentavelService.update(id, acaoSustentavel);
        ResponseAcaoSustentavelDTO acaoSustentavelUpdateDTO = modelMapper.map(acaoSustentavelUpdate, ResponseAcaoSustentavelDTO.class);

        return ResponseEntity.ok(acaoSustentavelUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        this.acaoSustentavelService.delete(id);

        return ResponseEntity.ok("A ação sustentável foi deletada com sucesso.");
    }
}
