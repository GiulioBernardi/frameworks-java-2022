package br.com.bluesoft.alucar.service;

import br.com.bluesoft.alucar.model.Aluguel;
import br.com.bluesoft.alucar.model.form.AluguelCarroForm;
import br.com.bluesoft.alucar.repository.AluguelRepository;
import br.com.bluesoft.alucar.repository.CarroRepository;
import br.com.bluesoft.alucar.repository.ClienteRepository;
import br.com.bluesoft.alucar.repository.VendedorRepository;
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