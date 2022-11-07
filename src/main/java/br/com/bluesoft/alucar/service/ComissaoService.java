package br.com.bluesoft.alucar.service;

import br.com.bluesoft.alucar.model.Comissao;
import br.com.bluesoft.alucar.model.ContaCorrente;
import br.com.bluesoft.alucar.model.Vendedor;
import br.com.bluesoft.alucar.model.dto.ComissaoDTO;
import br.com.bluesoft.alucar.repository.ComissaoRepository;
import br.com.bluesoft.alucar.repository.ContaCorrenteRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ComissaoService {

    private ComissaoRepository comissaoRepository;

    ContaCorrenteRepository contaCorrenteRepository;

    public ComissaoService(ComissaoRepository comissaoRepository, ContaCorrenteRepository contaCorrenteRepository){
        this.comissaoRepository = comissaoRepository;
        this.contaCorrenteRepository = contaCorrenteRepository;
    }

    public void cadastrarComissao(Vendedor vendedor, BigDecimal valorDaVenda ){

        ContaCorrente contaCorrente = contaCorrenteRepository.buscaPorVendedor(vendedor.getCpf());
        Boolean isVendedorVeterano = ChronoUnit.YEARS.between(vendedor.getDataAdmissao(), LocalDate.now()) > 5;

        Comissao comissao = new Comissao(vendedor, contaCorrente);

        if(isVendedorVeterano){
            BigDecimal valorComissao = comissao.calculaComissaoParaVeterano(valorDaVenda);
            comissao.setValor(valorComissao);
        }else{
            BigDecimal valorComissao = comissao.calculaComissaoPadrao(valorDaVenda);
            comissao.setValor(valorComissao);
        }
        comissaoRepository.save(comissao);
    }

    public List<ComissaoDTO> buscaAgrupaPorVendedor() {
        List<ComissaoDTO> comissaoDTO = comissaoRepository.agrupaPorVendedor();
        if(comissaoDTO.isEmpty()){
            throw new NoSuchElementException();
        }
        return comissaoDTO;
    }

    public ComissaoDTO buscaComissaoPorCpf(Long cpf) {
        Optional<ComissaoDTO> comissaoOptional = comissaoRepository.agrupaPorVendedorByCpf(cpf);
        if(comissaoOptional.isEmpty()){
            throw new NoSuchElementException();
        }
        return comissaoOptional.get();
    }
}
