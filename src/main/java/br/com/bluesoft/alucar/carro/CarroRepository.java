package br.com.bluesoft.alucar.carro;

import br.com.bluesoft.alucar.carro.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarroRepository extends JpaRepository<Carro, String> {
    Optional<Carro> findByPlaca(String placa);
}
