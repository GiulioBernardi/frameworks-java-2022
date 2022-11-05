package br.com.bluesoft.alucar.controller;

import br.com.bluesoft.alucar.model.Comissao;
import br.com.bluesoft.alucar.model.dto.ComissaoDTO;
import br.com.bluesoft.alucar.repository.CarroRepository;
import br.com.bluesoft.alucar.repository.ComissaoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@RestController
@RequestMapping("/comissões")
public class ComissaoController {

    ComissaoRepository comissaoRepository;
    private EntityManager entityManager;


    public ComissaoController(ComissaoRepository comissaoRepository,
                              EntityManager entityManager){
        this.comissaoRepository = comissaoRepository;
        this.entityManager = entityManager;
    }


    public List<ComissaoDTO> comissaoAgrupadoPorVendedor() {
        TypedQuery<ComissaoDTO> query = entityManager.createQuery("SELECT sum(c.valor), c.vendedor, c.contaCorrente FROM Comissao c group by c.vendedor", ComissaoDTO.class);
        return query.getResultList();
    }



    @GetMapping
    public List<ComissaoDTO> getComissoes(){
        List<ComissaoDTO> comissoes = comissaoRepository.agrupaPorVendedor();
        return comissoes;
    }

    @GetMapping("/{cpf}")
    public ComissaoDTO getComissaoPorCof(@PathVariable Long cpf){
        return comissaoRepository.agrupaPorVendedorByCpf(cpf);
    }



}
