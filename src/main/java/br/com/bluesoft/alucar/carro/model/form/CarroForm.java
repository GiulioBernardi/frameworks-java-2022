package br.com.bluesoft.alucar.carro.model.form;

import br.com.bluesoft.alucar.carro.model.Carro;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class CarroForm {

    @NotNull @NotEmpty @Pattern(regexp = "(^[a-zA-Z]{3}\\-)(\\d{4}$)")
    private String placa;

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

    //todo transformar tudo para Capitalize
    public Carro formToCarro(){
        return new Carro(placa,
                marca,
                modelo,
                cor,
                ano,
                quilometragem,
                diaria);
    }



    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

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
}
