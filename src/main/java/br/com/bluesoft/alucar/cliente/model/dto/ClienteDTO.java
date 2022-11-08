package br.com.bluesoft.alucar.cliente.model.dto;

import br.com.bluesoft.alucar.cliente.model.Cliente;
import br.com.bluesoft.alucar.endereco.model.Endereco;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteDTO {

    private String nomeCompleto;
    private Long cpf;
    private Endereco endereco;
    private String email;
    private Long celular;


    public ClienteDTO(Cliente cliente) {
        this.nomeCompleto = cliente.getNomeCompleto();
        this.cpf = cliente.getCpf();
//        this.endereco = cliente.getEndereco();
        this.email = cliente.getEmail();
        this.celular = cliente.getCelular();
    }

    public static List<ClienteDTO> clienteToDto(List<Cliente> clientes){
        return clientes.stream().map(ClienteDTO::new).collect(Collectors.toList());
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
