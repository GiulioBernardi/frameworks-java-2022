package br.com.bluesoft.alucar.vendedor.model.form;

import br.com.bluesoft.alucar.vendedor.model.Vendedor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

public class VendedorForm {

    @NotNull @NotEmpty
    private String nomeCompleto;

    @NotNull @NotEmpty
    private Long cpf;

    @NotNull @NotEmpty @PastOrPresent
    private LocalDate dataAdmissao;

    public Vendedor formToVendedor(){
        return new Vendedor(nomeCompleto, cpf, dataAdmissao);
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
