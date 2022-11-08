package br.com.bluesoft.alucar.aluguel.model.form;

import br.com.bluesoft.alucar.aluguel.model.Aluguel;
import br.com.bluesoft.alucar.carro.model.Carro;
import br.com.bluesoft.alucar.cliente.model.Cliente;
import br.com.bluesoft.alucar.vendedor.model.Vendedor;
import br.com.bluesoft.alucar.carro.CarroService;
import br.com.bluesoft.alucar.cliente.ClienteService;
import br.com.bluesoft.alucar.vendedor.VendedorService;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AluguelCarroForm {


    @NotNull
    @NotEmpty
//    @Pattern(regexp = "([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})\n")
    private Long cpfCliente;

    @NotNull
    @NotEmpty
//    @Pattern(regexp = "([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})\n")
    private Long cpfVendedor;

    @NotNull
    @NotEmpty
    private String placaDoCarro;

    @NotNull
    @NotEmpty
    private int qtdDiasAluguel;

    public Aluguel formToAluguel(ClienteService clienteService, VendedorService vendedorService, CarroService carroService){

        Cliente cliente = clienteService.obterPorCpfModel(cpfCliente);
        Vendedor vendedor = vendedorService.obterPorCpfModel(cpfVendedor);
        Carro carro = carroService.obterPorPlacaModel(placaDoCarro);

        return new Aluguel(cliente, vendedor, carro, qtdDiasAluguel);
    }

    public Long getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(Long cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public Long getCpfVendedor() {
        return cpfVendedor;
    }

    public void setCpfVendedor(Long cpfVendedor) {
        this.cpfVendedor = cpfVendedor;
    }

    public String getPlacaDoCarro() {
        return placaDoCarro;
    }

    public void setPlacaDoCarro(String placaDoCarro) {
        this.placaDoCarro = placaDoCarro;
    }

    public int getQtdDiasAluguel() {
        return qtdDiasAluguel;
    }

    public void setQtdDiasAluguel(int qtdDiasAluguel) {
        this.qtdDiasAluguel = qtdDiasAluguel;
    }
}
