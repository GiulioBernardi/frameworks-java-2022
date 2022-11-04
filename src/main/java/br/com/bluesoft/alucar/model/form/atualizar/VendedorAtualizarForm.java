package br.com.bluesoft.alucar.model.form.atualizar;

import br.com.bluesoft.alucar.model.Cliente;
import br.com.bluesoft.alucar.model.Vendedor;
import br.com.bluesoft.alucar.repository.VendedorRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

public class VendedorAtualizarForm {

    @NotNull
    @NotEmpty
    private String nomeCompleto;
    @NotNull
    @NotEmpty
    private Long cpf;
    @NotNull
    @NotEmpty
    @PastOrPresent
    private LocalDate dataAdmissao;

    public Vendedor atualizar(Long cpf, VendedorRepository vendedorRepository){
        Optional<Vendedor> vendedorOptional = vendedorRepository.findByCpf(cpf);
        if(vendedorOptional.isEmpty()){
            throw new NoSuchElementException();
        }
        Vendedor vendedor = vendedorOptional.get();
        vendedor.setNomeCompleto(this.nomeCompleto);
        vendedor.setCpf(this.cpf);
        vendedor.setDataAdmissao(this.dataAdmissao);
        return vendedor;
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
