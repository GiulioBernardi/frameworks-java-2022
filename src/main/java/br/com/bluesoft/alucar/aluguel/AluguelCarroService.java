package br.com.bluesoft.alucar.aluguel;

import br.com.bluesoft.alucar.aluguel.model.Aluguel;
import br.com.bluesoft.alucar.aluguel.model.form.AluguelCarroForm;
import br.com.bluesoft.alucar.carro.CarroRepository;
import br.com.bluesoft.alucar.cliente.ClienteRepository;
import br.com.bluesoft.alucar.vendedor.VendedorRepository;
import br.com.bluesoft.alucar.carro.CarroService;
import br.com.bluesoft.alucar.cliente.ClienteService;
import br.com.bluesoft.alucar.comissao.ComissaoService;
import br.com.bluesoft.alucar.vendedor.VendedorService;
import org.springframework.stereotype.Service;

@Service
public class AluguelCarroService {

    AluguelRepository aluguelRepository;
    ClienteRepository clienteRepository;
    VendedorRepository vendedorRepository;
    CarroRepository carroRepository;
    ComissaoService comissaoService;
    ClienteService clienteService;
    VendedorService vendedorService;

    CarroService carroService;
    public AluguelCarroService(ClienteRepository clienteRepository,
                               VendedorRepository vendedorRepository,
                               CarroRepository carroRepository,
                               AluguelRepository aluguelRepository,
                               ComissaoService comissaoService,
                               ClienteService clienteService,
                               CarroService carroService,
                               VendedorService vendedorService){
        this.carroRepository = carroRepository;
        this.vendedorRepository = vendedorRepository;
        this.clienteRepository = clienteRepository;
        this.comissaoService = comissaoService;
        this.aluguelRepository = aluguelRepository;
        this.clienteService = clienteService;
        this.vendedorService = vendedorService;
        this.carroService = carroService;
    }
    public Aluguel salvar(AluguelCarroForm vendaCarroForm) {

        Aluguel aluguel = vendaCarroForm.formToAluguel(clienteService, vendedorService, carroService);

        comissaoService.cadastrarComissao(aluguel.getVendedor(), aluguel.getValorTotalAluguel());

        aluguelRepository.save(aluguel);
        return aluguel;
    }
}