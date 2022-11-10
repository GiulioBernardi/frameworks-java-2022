package br.com.bluesoft.alucar.endereco;

import br.com.bluesoft.alucar.endereco.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    @Modifying
    @Query(value = "delete from endereco where endereco.cliente_key = (select cliente_key from cliente where cpf = :cpf)))", nativeQuery = true)
    void deletaEnderecoPorIdCliente(@Param("cpf") Long cpf);

    void deleteByCliente_Id(Long Id);

}
