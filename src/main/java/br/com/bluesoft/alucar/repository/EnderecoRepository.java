package br.com.bluesoft.alucar.repository;

import br.com.bluesoft.alucar.model.ContaCorrente;
import br.com.bluesoft.alucar.model.Endereco;
import org.hibernate.cfg.JPAIndexHolder;
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

//    @Modifying
//    @Query("delete from Endereco e where e.cliente.cpf = :cpf")
//    void deletaEnderecoPorIdCliente(@Param("cpf") Long cpf);


    void deleteByCliente_Id(Long Id);

}
