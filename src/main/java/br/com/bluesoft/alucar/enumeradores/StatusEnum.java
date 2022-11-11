package br.com.bluesoft.alucar.enumeradores;

public enum StatusEnum {

    ATIVO(1), INATIVO(0);

    private int valor;
    StatusEnum(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
