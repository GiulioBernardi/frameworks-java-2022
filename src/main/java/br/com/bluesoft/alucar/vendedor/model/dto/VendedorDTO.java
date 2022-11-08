package br.com.bluesoft.alucar.vendedor.model.dto;

import br.com.bluesoft.alucar.vendedor.model.Vendedor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class VendedorDTO {
    private String nomeCompleto;
    private Long cpf;
    private LocalDate dataAdmissao;

    public VendedorDTO(Vendedor vendedor){
        this.nomeCompleto = vendedor.getNomeCompleto();
        this.cpf = vendedor.getCpf();
        this.dataAdmissao = vendedor.getDataAdmissao();
    }

    public static List<VendedorDTO> vendedorToDto(List<Vendedor> vendedores){
        return vendedores.stream().map(VendedorDTO::new).collect(Collectors.toList());
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
