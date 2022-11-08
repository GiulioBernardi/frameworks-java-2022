package br.com.bluesoft.alucar.aluguel.model.dto;

public class AluguelCarroDTO {

    private Long cpfCliente;

    private Long cpfVendedor;

    private String placaCarro;

    private Integer qtdDiasAluguel;

    public AluguelCarroDTO(Long cpfCliente, Long cpfVendedor, String placaCarro, Integer qtdDiasAluguel) {
        this.cpfCliente = cpfCliente;
        this.cpfVendedor = cpfVendedor;
        this.placaCarro = placaCarro;
        this.qtdDiasAluguel = qtdDiasAluguel;
    }

    public AluguelCarroDTO() {
    }

    public Long getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(Long cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public Long getCpfVendedor() {
        return cpfVendedor;
    }

    public void setCpfVendedor(Long cpfVendedor) {
        this.cpfVendedor = cpfVendedor;
    }

    public String getPlacaCarro() {
        return placaCarro;
    }

    public void setPlacaCarro(String placaCarro) {
        this.placaCarro = placaCarro;
    }

    public Integer getQtdDiasAluguel() {
        return qtdDiasAluguel;
    }

    public void setQtdDiasAluguel(Integer qtdDiasAluguel) {
        this.qtdDiasAluguel = qtdDiasAluguel;
    }
}
