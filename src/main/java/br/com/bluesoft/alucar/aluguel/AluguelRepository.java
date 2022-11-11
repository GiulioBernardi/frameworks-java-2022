package br.com.bluesoft.alucar.aluguel;

import br.com.bluesoft.alucar.aluguel.model.Aluguel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Long> {

    @Query("select a from Aluguel a where a.status = 1")
    List<Aluguel> todosAtivos();

    @Query("select a from Aluguel a where a.status = 0")
    List<Aluguel> todosInativos();


}
