package br.com.bluesoft.alucar.aluguel;

import br.com.bluesoft.alucar.aluguel.model.Aluguel;
import br.com.bluesoft.alucar.aluguel.model.dto.AluguelDTO;
import br.com.bluesoft.alucar.aluguel.model.form.AluguelCarroForm;
import br.com.bluesoft.alucar.carro.CarroService;
import br.com.bluesoft.alucar.cliente.ClienteService;
import br.com.bluesoft.alucar.comissao.ComissaoService;
import br.com.bluesoft.alucar.enumeradores.StatusEnum;
import br.com.bluesoft.alucar.vendedor.VendedorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AluguelCarroService {
    AluguelRepository aluguelRepository;
    ComissaoService comissaoService;
    ClienteService clienteService;
    VendedorService vendedorService;
    CarroService carroService;
    public AluguelCarroService(AluguelRepository aluguelRepository,
                               ComissaoService comissaoService,
                               ClienteService clienteService,
                               CarroService carroService,
                               VendedorService vendedorService){
        this.comissaoService = comissaoService;
        this.aluguelRepository = aluguelRepository;
        this.clienteService = clienteService;
        this.vendedorService = vendedorService;
        this.carroService = carroService;
    }

    public Aluguel salvar(AluguelCarroForm vendaCarroForm) {
        Aluguel aluguel = vendaCarroForm.formToAluguel(clienteService, vendedorService, carroService);
        //todo aluguel que calcula a comissao e retorna valor da comissao
        //todo comissaoService salva a comissao
        comissaoService.cadastrarComissao(aluguel.getVendedor(), aluguel.getValorTotalAluguel());
        aluguelRepository.save(aluguel);
        return aluguel;
    }

    public List<AluguelDTO> obterTodosAtivos() {
        List<Aluguel> alugueis = aluguelRepository.todosAtivos();
        if(alugueis.isEmpty()){
            throw new NoSuchElementException();
        }
        return AluguelDTO.aluguelToDto(alugueis);
    }

    public List<AluguelDTO> obterTodosApagados() {
        List<Aluguel> alugueis = aluguelRepository.todosInativos();
        if(alugueis.isEmpty()){
            throw new NoSuchElementException();
        }
        return AluguelDTO.aluguelToDto(alugueis);
    }

    public void deletarLogicamente(Long id) {
        Optional<Aluguel> aluguelOptional = aluguelRepository.findById(id);
        if(aluguelOptional.isEmpty()){
            throw new NoSuchElementException();
        }
        Aluguel aluguel = aluguelOptional.get();
        int inativo = StatusEnum.INATIVO.getValor();
        aluguel.setStatus(inativo);
        aluguelRepository.save(aluguel);
    }
}