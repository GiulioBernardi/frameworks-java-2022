package br.com.bluesoft.alucar.carro;

import br.com.bluesoft.alucar.carro.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarroRepository extends JpaRepository<Carro, String> {
    Optional<Carro> findByPlaca(String placa);

    @Query(value = "select * from carro c where c.status = 0", nativeQuery = true)
    List<Carro> findAtivos();

    @Query(value = "select * from carro c where c.status = 1", nativeQuery = true)
    List<Carro> findInativos();
}
