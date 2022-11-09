package br.com.bluesoft.alucar.vendedor.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Vendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vendedor_key")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nomeCompleto;

    @Column(nullable = false)
    private Long cpf;

    @Column(nullable = false)
    private LocalDate dataAdmissao;

    public Vendedor() {
    }

    public Vendedor(Long id, String nomeCompleto, Long cpf, LocalDate dataAdmissao) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.dataAdmissao = dataAdmissao;
    }

    public Vendedor(String nomeCompleto, Long cpf, LocalDate dataAdmissao) {
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.dataAdmissao = dataAdmissao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }
}
