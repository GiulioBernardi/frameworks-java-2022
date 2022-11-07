package br.com.bluesoft.alucar.repository;

import br.com.bluesoft.alucar.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByCpf(Long cpf);

    Optional<Cliente> findByEmail(String email);
}
