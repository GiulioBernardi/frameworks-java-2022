package br.com.bluesoft.alucar.comissao.model.dto;

import br.com.bluesoft.alucar.comissao.model.Comissao;
import br.com.bluesoft.alucar.contaCorrente.model.ContaCorrente;
import br.com.bluesoft.alucar.contaCorrente.model.dto.ContaCorrenteDTO;
import br.com.bluesoft.alucar.vendedor.model.Vendedor;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ComissaoDTO {
    private String vendedor;
    private Long cpf;
    private BigDecimal valor;
    private ContaCorrenteDTO conta;

    public ComissaoDTO(Comissao comissao){
        this.vendedor = comissao.getVendedor().getNomeCompleto();
        this.cpf = comissao.getVendedor().getCpf();
        this.valor = comissao.getValor();
        this.conta = new ContaCorrenteDTO(comissao.getContaCorrente());
    }

    public ComissaoDTO(Vendedor vendedor, BigDecimal valor, ContaCorrente contaCorrente){
        this.vendedor = vendedor.getNomeCompleto();
        this.cpf = vendedor.getCpf();
        this.valor = valor;
        this.conta = new ContaCorrenteDTO(contaCorrente);
    }

    public static List<ComissaoDTO> comissaoToDto(List<Comissao> comissoes){
        return comissoes.stream().map(ComissaoDTO::new).collect(Collectors.toList());
    }

    public String getVendedor() {
        return vendedor;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public ContaCorrenteDTO getConta() {
        return conta;
    }

    public void setConta(ContaCorrenteDTO conta) {
        this.conta = conta;
    }
}