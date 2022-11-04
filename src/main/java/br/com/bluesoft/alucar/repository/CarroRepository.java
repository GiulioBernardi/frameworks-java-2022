package br.com.bluesoft.alucar.repository;

import br.com.bluesoft.alucar.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarroRepository extends JpaRepository<Carro, String> {
    Carro findByPlaca(String placa);
}
