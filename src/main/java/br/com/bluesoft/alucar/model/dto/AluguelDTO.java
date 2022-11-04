package br.com.bluesoft.alucar.model.dto;

import br.com.bluesoft.alucar.model.Aluguel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class AluguelDTO {

    private String nomeCliente;
    private String modeloCarro;
    private String placaCarro;
    private String vendedor;

    private Integer quantidadeDias;
    private BigDecimal valorTotal;
    private LocalDate dataAluguel;
    public AluguelDTO(Aluguel aluguel) {
        this.nomeCliente = aluguel.getCliente().getNomeCompleto();
        this.modeloCarro = aluguel.getModeloCarro();
        this.placaCarro = aluguel.getCarro().getPlaca();
        this.vendedor = aluguel.getVendedor().getNomeCompleto();
        this.quantidadeDias = aluguel.getDiasComOCarro();
        this.valorTotal = aluguel.getValorTotalAluguel();
        this.dataAluguel = aluguel.getDataDoAluguel();
    }

    public static List<AluguelDTO> aluguelToDto(List<Aluguel> alugueis){
        return alugueis.stream().map(AluguelDTO::new).collect(Collectors.toList());
    }


    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getModeloCarro() {
        return modeloCarro;
    }

    public void setModeloCarro(String modeloCarro) {
        this.modeloCarro = modeloCarro;
    }

    public String getPlacaCarro() {
        return placaCarro;
    }

    public void setPlacaCarro(String placaCarro) {
        this.placaCarro = placaCarro;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public Integer getQuantidadeDias() {
        return quantidadeDias;
    }

    public void setQuantidadeDias(Integer quantidadeDias) {
        this.quantidadeDias = quantidadeDias;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDate getDataAluguel() {
        return dataAluguel;
    }

    public void setDataAluguel(LocalDate dataAluguel) {
        this.dataAluguel = dataAluguel;
    }
}
