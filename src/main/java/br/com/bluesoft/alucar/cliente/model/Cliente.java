package br.com.bluesoft.alucar.cliente.model;

import br.com.bluesoft.alucar.endereco.model.Endereco;

import javax.persistence.*;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_key")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nomeCompleto;

    @Column(nullable = false)
    private Long cpf;

//    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @Transient
//    private Endereco endereco;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Long celular;

    public Cliente() {
    }

    public Cliente(String nomeCompleto, Long cpf, Endereco endereco, String email, Long celular) {
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
//        this.endereco = endereco;
        this.email = email;
        this.celular = celular;
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

//    public Endereco getEndereco() {
//        return endereco;
//    }
//
//    public void setEndereco(Endereco endereco) {
//        this.endereco = endereco;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCelular() {
        return celular;
    }

    public void setCelular(Long celular) {
        this.celular = celular;
    }
}
