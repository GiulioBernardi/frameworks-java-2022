package br.com.bluesoft.alucar.service;

import br.com.bluesoft.alucar.comissao.ComissaoRepository;
import br.com.bluesoft.alucar.comissao.ComissaoService;
import br.com.bluesoft.alucar.contaCorrente.ContaCorrenteRepository;
import br.com.bluesoft.alucar.contaCorrente.model.ContaCorrente;
import br.com.bluesoft.alucar.vendedor.model.Vendedor;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComissaoServiceTest {

    ComissaoRepository comissaoRepository;
    ContaCorrenteRepository contaCorrenteRepository;
    ComissaoService comissaoService = new ComissaoService(comissaoRepository, contaCorrenteRepository);

    private static final DecimalFormat df = new DecimalFormat("0.00");


    @Test
    void deveCalcularComissaoParaVeteranoSeVendedorTiverMaisQueCincoAnos(){
        BigDecimal VALOR_TOTAL_DA_COMPRA = BigDecimal.valueOf(95.59);

        Vendedor vendedorVeterano = new Vendedor();
        vendedorVeterano.setId(1l);
        vendedorVeterano.setNomeCompleto("Giulio Cesar Costa Bernardi");
        vendedorVeterano.setCpf(14785236985l);
        vendedorVeterano.setDataAdmissao(LocalDate.of(2017, 01, 10));

        ContaCorrente contaCorrente = new ContaCorrente();
        contaCorrente.setId(1l);
        contaCorrente.setContaCorrente(1234);
        contaCorrente.setAgencia(123);
        contaCorrente.setBanco("Itau");
        contaCorrente.setVendedor(vendedorVeterano);

        BigDecimal comissaoVendedor = comissaoService.calculaComissaoDaVenda(vendedorVeterano, VALOR_TOTAL_DA_COMPRA);

        //95.59 * 0,13 = 12.42
        BigDecimal valorEsperado = VALOR_TOTAL_DA_COMPRA
                .multiply(BigDecimal.valueOf(0.13))
                .setScale(2,BigDecimal.ROUND_DOWN);;
        assertEquals(comissaoVendedor, valorEsperado);
    }

    @Test
    void deveCalcularComissaoParaVeteranoSeVendedorTiverMaisQueCincoAnos2(){
        BigDecimal VALOR_TOTAL_DA_COMPRA = BigDecimal.valueOf(120);

        Vendedor vendedorVeterano = new Vendedor();
        vendedorVeterano.setId(1l);
        vendedorVeterano.setNomeCompleto("Giulio Cesar Costa Bernardi");
        vendedorVeterano.setCpf(14785236985l);
        vendedorVeterano.setDataAdmissao(LocalDate.of(2017, 01, 10));

        ContaCorrente contaCorrente = new ContaCorrente();
        contaCorrente.setId(1l);
        contaCorrente.setContaCorrente(1234);
        contaCorrente.setAgencia(123);
        contaCorrente.setBanco("Itau");
        contaCorrente.setVendedor(vendedorVeterano);

        BigDecimal comissaoVendedor = comissaoService.calculaComissaoDaVenda(vendedorVeterano, VALOR_TOTAL_DA_COMPRA);

        //120 * 0,13 = 15.60
        BigDecimal valorEsperado = VALOR_TOTAL_DA_COMPRA
                .multiply(BigDecimal.valueOf(0.13))
                .setScale(2,BigDecimal.ROUND_DOWN);
        assertEquals(comissaoVendedor, valorEsperado);
    }

    @Test
    void deveCalcularComissaoParaVeteranoSeVendedorTiverMaisQueCincoAnos3(){
        BigDecimal VALOR_TOTAL_DA_COMPRA = BigDecimal.valueOf(260.63);

        Vendedor vendedorVeterano = new Vendedor();
        vendedorVeterano.setId(1l);
        vendedorVeterano.setNomeCompleto("Giulio Cesar Costa Bernardi");
        vendedorVeterano.setCpf(14785236985l);
        vendedorVeterano.setDataAdmissao(LocalDate.of(2017, 01, 10));

        ContaCorrente contaCorrente = new ContaCorrente();
        contaCorrente.setId(1l);
        contaCorrente.setContaCorrente(1234);
        contaCorrente.setAgencia(123);
        contaCorrente.setBanco("Itau");
        contaCorrente.setVendedor(vendedorVeterano);

        BigDecimal comissaoVendedor = comissaoService.calculaComissaoDaVenda(vendedorVeterano, VALOR_TOTAL_DA_COMPRA);

        //260,63 * 0,13 = 33.88
        BigDecimal valorEsperado = VALOR_TOTAL_DA_COMPRA
                                .multiply(BigDecimal.valueOf(0.13))
                                .setScale(2,BigDecimal.ROUND_DOWN);;
        assertEquals(comissaoVendedor, valorEsperado);
    }

}
