package br.com.bluesoft.alucar.aluguel;

import br.com.bluesoft.alucar.aluguel.model.Aluguel;
import br.com.bluesoft.alucar.aluguel.model.dto.AluguelDTO;
import br.com.bluesoft.alucar.aluguel.model.form.AluguelCarroForm;
import br.com.bluesoft.alucar.carro.CarroRepository;
import br.com.bluesoft.alucar.cliente.ClienteRepository;
import br.com.bluesoft.alucar.comissao.model.Comissao;
import br.com.bluesoft.alucar.contaCorrente.ContaCorrenteRepository;
import br.com.bluesoft.alucar.contaCorrente.model.ContaCorrente;
import br.com.bluesoft.alucar.vendedor.VendedorRepository;
import br.com.bluesoft.alucar.carro.CarroService;
import br.com.bluesoft.alucar.cliente.ClienteService;
import br.com.bluesoft.alucar.comissao.ComissaoService;
import br.com.bluesoft.alucar.vendedor.VendedorService;
import br.com.bluesoft.alucar.vendedor.model.Vendedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AluguelCarroService {

    AluguelRepository aluguelRepository;
//    ClienteRepository clienteRepository;
//    VendedorRepository vendedorRepository;
//    CarroRepository carroRepository;
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
//        this.carroRepository = carroRepository;
//        this.vendedorRepository = vendedorRepository;
//        this.clienteRepository = clienteRepository;
        this.comissaoService = comissaoService;
        this.aluguelRepository = aluguelRepository;
        this.clienteService = clienteService;
        this.vendedorService = vendedorService;
        this.carroService = carroService;
    }

//    private final double COMISSAO_NOVATO = 0.1;
//    private final double COMISSAO_VETERANO = 0.13;
//    private final int ANOS_DE_CASA_VETERANO = 5;

    public Aluguel salvar(AluguelCarroForm vendaCarroForm) {
        Aluguel aluguel = vendaCarroForm.formToAluguel(clienteService, vendedorService, carroService);
        //todo aluguel que calcula a comissao e retorna valor da comissao
        //todo comissaoService salva a comissao
        comissaoService.cadastrarComissao(aluguel.getVendedor(), aluguel.getValorTotalAluguel());
        aluguelRepository.save(aluguel);
        return aluguel;
    }

    public List<AluguelDTO> obterTodos() {
        List<Aluguel> alugueis = aluguelRepository.findAll();
        if(alugueis.isEmpty()){
            throw new NoSuchElementException();
        }
        return AluguelDTO.aluguelToDto(alugueis);


    }

//    public BigDecimal calculaComissaoDaVenda(Vendedor vendedor, BigDecimal valor_total_da_compra) {
//        Period anosQueOVendedorEstaNaEmpresa = Period.between(vendedor.getDataAdmissao(), LocalDate.now());
//        int anos_de_casa_do_vendedor = anosQueOVendedorEstaNaEmpresa.getYears();
//        if(anos_de_casa_do_vendedor >= ANOS_DE_CASA_VETERANO){
//            return valor_total_da_compra.multiply(BigDecimal.valueOf(COMISSAO_VETERANO));
//        }else{
//            return valor_total_da_compra.multiply(BigDecimal.valueOf(COMISSAO_NOVATO));
//        }
//    }
}