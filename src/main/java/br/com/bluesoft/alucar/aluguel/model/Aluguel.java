package br.com.bluesoft.alucar.aluguel.model;


import br.com.bluesoft.alucar.carro.model.Carro;
import br.com.bluesoft.alucar.cliente.model.Cliente;
import br.com.bluesoft.alucar.vendedor.model.Vendedor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Aluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aluguel_key")
    private Long id;

    @Column(name = "modelo")
    private String modeloCarro;

    @Column(name = "dias")
    private Integer diasComOCarro;
    @Column(name = "valor_aluguel")
    private BigDecimal valorTotalAluguel;
    @Column(name = "data_aluguel")
    private LocalDate dataDoAluguel;

    @OneToOne
    @JoinColumn(name="cliente_key")
    private Cliente cliente;
    @OneToOne
    @JoinColumn(name="placa")
    private Carro carro;
    @OneToOne
    @JoinColumn(name="vendedor_key")
    private Vendedor vendedor;

    public Aluguel(Cliente cliente, String modeloCarro, Carro carro, Vendedor vendedor, Integer diasComOCarro, BigDecimal valorTotalAluguel, LocalDate dataDoAluguel) {
        this.cliente = cliente;
        this.modeloCarro = modeloCarro;
        this.carro = carro;
        this.vendedor = vendedor;
        this.diasComOCarro = diasComOCarro;
        this.valorTotalAluguel = valorTotalAluguel;
        this.dataDoAluguel = dataDoAluguel;
    }

    public Aluguel(Cliente cliente, Vendedor vendedor, Carro carro, Integer diasComOCarro) {
        this.cliente = cliente;
        this.carro = carro;
        this.vendedor = vendedor;
        this.diasComOCarro = diasComOCarro;
        this.dataDoAluguel = LocalDate.now();
        this.modeloCarro = carro.getModelo();
        this.valorTotalAluguel = this.calculaValorTotal(this.getDiasComOCarro(), carro.getDiaria());
    }

    public Aluguel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getModeloCarro() {
        return modeloCarro;
    }

    public void setModeloCarro(String modeloCarro) {
        this.modeloCarro = modeloCarro;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Integer getDiasComOCarro() {
        return diasComOCarro;
    }

    public void setDiasComOCarro(Integer diasComOCarro) {
        this.diasComOCarro = diasComOCarro;
    }

    public BigDecimal getValorTotalAluguel() {
        return valorTotalAluguel;
    }

    public void setValorTotalAluguel(BigDecimal valorTotalAluguel) {
        this.valorTotalAluguel = valorTotalAluguel;
    }

    public LocalDate getDataDoAluguel() {
        return dataDoAluguel;
    }

    public void setDataDoAluguel(LocalDate dataDoAluguel) {
        this.dataDoAluguel = dataDoAluguel;
    }

    private BigDecimal calculaValorTotal(Integer diasComOCarro, BigDecimal valorDaDiaria){
        return BigDecimal.valueOf(diasComOCarro).multiply(valorDaDiaria);
    }
}