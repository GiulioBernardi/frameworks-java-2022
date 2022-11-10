package br.com.bluesoft.alucar.comissao;

import br.com.bluesoft.alucar.comissao.model.Comissao;
import br.com.bluesoft.alucar.comissao.model.dto.ComissaoDTO;
import br.com.bluesoft.alucar.contaCorrente.ContaCorrenteRepository;
import br.com.bluesoft.alucar.contaCorrente.model.ContaCorrente;
import br.com.bluesoft.alucar.vendedor.model.Vendedor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ComissaoService {

    private final double COMISSAO_NOVATO = 0.1;
    private final double COMISSAO_VETERANO = 0.13;
    private final int ANOS_DE_CASA_VETERANO = 5;

    private ComissaoRepository comissaoRepository;

    ContaCorrenteRepository contaCorrenteRepository;

    public ComissaoService(ComissaoRepository comissaoRepository, ContaCorrenteRepository contaCorrenteRepository){
        this.comissaoRepository = comissaoRepository;
        this.contaCorrenteRepository = contaCorrenteRepository;
    }

    public void cadastrarComissao(Vendedor vendedor, BigDecimal valorDaVenda ){
        ContaCorrente contaCorrente = contaCorrenteRepository.buscaPorVendedor(vendedor.getCpf());

        Comissao comissao = new Comissao(vendedor, contaCorrente);
        comissao.setValor(calculaComissaoDaVenda(vendedor, valorDaVenda));

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

    public BigDecimal calculaComissaoDaVenda(Vendedor vendedor, BigDecimal valorDaVenda) {
        Period anosQueOVendedorEstaNaEmpresa = Period.between(vendedor.getDataAdmissao(), LocalDate.now());
        int anos_de_casa_do_vendedor = anosQueOVendedorEstaNaEmpresa.getYears();
        if(anos_de_casa_do_vendedor >= ANOS_DE_CASA_VETERANO){
            return valorDaVenda.multiply(BigDecimal.valueOf(COMISSAO_VETERANO))
                    .setScale(2, BigDecimal.ROUND_DOWN);
        }else{
            return valorDaVenda.multiply(BigDecimal.valueOf(COMISSAO_NOVATO))
                    .setScale(2, BigDecimal.ROUND_DOWN);
        }
    }
}
