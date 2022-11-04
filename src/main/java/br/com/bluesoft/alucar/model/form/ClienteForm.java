package br.com.bluesoft.alucar.model.form;

import br.com.bluesoft.alucar.model.Cliente;
import br.com.bluesoft.alucar.model.Endereco;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ClienteForm {

    @NotNull @NotEmpty
    private String nomeCompleto;

    @NotNull @NotEmpty
    private Long cpf;

    @NotNull @NotEmpty
    private Endereco endereco;

    @NotNull @NotEmpty
    private String email;

    @NotNull @NotEmpty
    private Long celular;

    public Cliente formToCliente(){
        return new Cliente(nomeCompleto, cpf, endereco, email, celular);
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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

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
