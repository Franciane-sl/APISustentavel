package APISustentavel.Repository;

import APISustentavel.Model.Entity.AcaoSustentavel;
import APISustentavel.Model.Enum.CategoriaEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcaoSustentavelRepository extends JpaRepository<AcaoSustentavel, Long> {

    List<AcaoSustentavel> findByCategoria(CategoriaEnum categoria);

}
