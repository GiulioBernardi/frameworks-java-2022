package br.com.bluesoft.alucar.repository;

import br.com.bluesoft.alucar.model.Comissao;
import br.com.bluesoft.alucar.model.dto.ComissaoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComissaoRepository extends JpaRepository<Comissao, Long> {

    @Query("select new br.com.bluesoft.alucar.model.dto.ComissaoDTO(c.vendedor, sum(c.valor), c.contaCorrente) from Comissao AS c GROUP BY c.vendedor")
    List<ComissaoDTO> agrupaPorVendedor();


    @Query("select new br.com.bluesoft.alucar.model.dto.ComissaoDTO(c.vendedor, sum(c.valor), c.contaCorrente) from Comissao AS c where c.vendedor.cpf = :cpf GROUP BY c.vendedor")
    ComissaoDTO agrupaPorVendedorByCpf(@Param("cpf")Long cpf);
}
