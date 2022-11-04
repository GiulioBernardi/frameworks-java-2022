package br.com.bluesoft.alucar.repository;

import br.com.bluesoft.alucar.model.ContaCorrente;
import org.hibernate.cfg.JPAIndexHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaCorrenteRepository extends JpaRepository<ContaCorrente, Long> {

    ContaCorrente findByVendedor(Long cpf);

    @Query(value = "select c from ContaCorrente c inner join Vendedor v on c.vendedor.cpf = v.cpf where v.cpf = :cpf")
    ContaCorrente buscaPorVendedor(@Param("cpf") Long cpf);
}
