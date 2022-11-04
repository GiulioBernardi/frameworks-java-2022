package br.com.bluesoft.alucar.service;

import br.com.bluesoft.alucar.model.Comissao;
import br.com.bluesoft.alucar.model.ContaCorrente;
import br.com.bluesoft.alucar.model.Vendedor;
import br.com.bluesoft.alucar.repository.ComissaoRepository;
import br.com.bluesoft.alucar.repository.ContaCorrenteRepository;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class ComissaoService {

    private ComissaoRepository comissaoRepository;

    ContaCorrenteRepository contaCorrenteRepository;

    public ComissaoService(ComissaoRepository comissaoRepository, ContaCorrenteRepository contaCorrenteRepository){
        this.comissaoRepository = comissaoRepository;
        this.contaCorrenteRepository = contaCorrenteRepository;
    }

    public void cadastrarComissao(Vendedor vendedor, BigDecimal valorDaVenda ){

        System.out.println("CPF VENDENDDDODOODOODOOR: " + vendedor.getCpf());
        ContaCorrente contaCorrente = contaCorrenteRepository.buscaPorVendedor(vendedor.getCpf());
        System.out.println("CONTA CORRENTE CARALHO: " + contaCorrente.getContaCorrente());
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
}
