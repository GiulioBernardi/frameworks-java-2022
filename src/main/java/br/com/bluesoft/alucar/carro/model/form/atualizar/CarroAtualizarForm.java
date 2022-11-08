package br.com.bluesoft.alucar.carro.model.form.atualizar;

import br.com.bluesoft.alucar.carro.model.Carro;
import br.com.bluesoft.alucar.carro.CarroRepository;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;

public class CarroAtualizarForm {

    @NotNull @NotEmpty
    private String marca;

    @NotNull @NotEmpty
    private String modelo;

    @NotNull @NotEmpty
    private String cor;

    @Positive
    private Integer ano;

    @PositiveOrZero(message = "O valor da quilometragem deve ser positivo")
    private Integer quilometragem;

    @Positive(message = "O valor da diária deve ser um número positivo")
    private BigDecimal diaria;


    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(Integer quilometragem) {
        this.quilometragem = quilometragem;
    }

    public BigDecimal getDiaria() {
        return diaria;
    }

    public void setDiaria(BigDecimal diaria) {
        this.diaria = diaria;
    }

    public Carro atualizar(String placa, CarroRepository carroRepository) {
        Optional<Carro> carroOptional = carroRepository.findByPlaca(placa);
        if(carroOptional.isEmpty()){
            throw new NoSuchElementException();
        }


        //todo transformar tudo para Capitalize
        Carro carro = carroOptional.get();
        carro.setMarca(this.marca);
        carro.setModelo(this.modelo);
        carro.setCor(this.cor);
        carro.setAno(this.ano);
        carro.setQuilometragem(this.quilometragem);
        carro.setDiaria(this.diaria);

        return carro;

    }
}
