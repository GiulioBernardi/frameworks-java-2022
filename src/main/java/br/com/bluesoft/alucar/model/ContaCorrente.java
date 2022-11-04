package br.com.bluesoft.alucar.model;

import javax.persistence.*;

@Entity
public class ContaCorrente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conta_corrente_key")
    private Long id;

    @Column(nullable = false)
    private String banco;

    @Column(nullable = false)
    private Integer agencia;

    @Column(nullable = false)
    private Integer contaCorrente;

    @OneToOne
    @JoinColumn(name = "vendedor_key")
    private Vendedor vendedor;

    public ContaCorrente() {
    }

    public ContaCorrente(String banco, Integer agencia, Integer contaCorrente, Vendedor vendedor) {
        this.banco = banco;
        this.agencia = agencia;
        this.contaCorrente = contaCorrente;
        this.vendedor = vendedor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
}
