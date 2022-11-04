package br.com.bluesoft.alucar.model.form;

import br.com.bluesoft.alucar.model.Aluguel;
import br.com.bluesoft.alucar.model.Carro;
import br.com.bluesoft.alucar.model.Cliente;
import br.com.bluesoft.alucar.model.Vendedor;
import br.com.bluesoft.alucar.repository.CarroRepository;
import br.com.bluesoft.alucar.repository.ClienteRepository;
import br.com.bluesoft.alucar.repository.VendedorRepository;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    public Aluguel formToAluguel(ClienteRepository clienteRepository, VendedorRepository vendedorRepository, CarroRepository carroRepository){
        Optional<Cliente> cliente = clienteRepository.findByCpf(cpfCliente);
        Optional<Vendedor> vendedor = vendedorRepository.findByCpf(cpfVendedor);
        Optional<Carro> carro = carroRepository.findByPlaca(placaDoCarro);
        if(carro.isEmpty() || cliente.isEmpty()){
            throw new NoSuchElementException();
        }
        return new Aluguel(cliente.get(), vendedor.get(), carro.get(), qtdDiasAluguel);
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
