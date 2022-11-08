package br.com.bluesoft.alucar.aluguel;

import br.com.bluesoft.alucar.aluguel.model.Aluguel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Long> {



}
