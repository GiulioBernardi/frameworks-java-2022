package br.com.bluesoft.alucar.model.dto;

import br.com.bluesoft.alucar.model.ContaCorrente;

import java.util.List;
import java.util.stream.Collectors;

public class ContaCorrenteDTO {

    private String banco;

    private Integer agencia;
    private Integer contaCorrente;

    public ContaCorrenteDTO(ContaCorrente contaCorrente) {
        this.banco = contaCorrente.getBanco();
        this.agencia = contaCorrente.getAgencia();
        this.contaCorrente = contaCorrente.getContaCorrente();
    }





    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public Integer getAgencia() {
        return agencia;
    }

    public void setAgencia(Integer agencia) {
        this.agencia = agencia;
    }

    public Integer getContaCorrente() {
        return contaCorrente;
    }

    public void setContaCorrente(Integer contaCorrente) {
        this.contaCorrente = contaCorrente;
    }
}
