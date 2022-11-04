package br.com.bluesoft.alucar.repository;

import br.com.bluesoft.alucar.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarroRepository extends JpaRepository<Carro, String> {
    Optional<Carro> findByPlaca(String placa);
}
