package br.com.bluesoft.alucar.repository;

import br.com.bluesoft.alucar.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Long> {

    Optional<Vendedor> findByCpf(Long cpf);
}
