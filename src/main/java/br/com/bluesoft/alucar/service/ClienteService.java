package br.com.bluesoft.alucar.service;

import br.com.bluesoft.alucar.model.Cliente;
import br.com.bluesoft.alucar.model.dto.ClienteDTO;
import br.com.bluesoft.alucar.model.form.ClienteForm;
import br.com.bluesoft.alucar.model.form.atualizar.ClienteAtualizarForm;
import br.com.bluesoft.alucar.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ClienteService {

    ClienteRepository clienteRepository;

    EnderecoService enderecoService;

    public ClienteService(ClienteRepository clienteRepository,
                          EnderecoService enderecoService){
        this.clienteRepository = clienteRepository;
        this.enderecoService = enderecoService;
    }


    public List<ClienteDTO> obterClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        if(clientes.isEmpty()){
            throw new NoSuchElementException();
        }
        return ClienteDTO.clienteToDto(clientes);
    }

    public ClienteDTO obterPorCpf(Long cpf) {
        Optional<Cliente> clienteOptional = clienteRepository.findByCpf(cpf);
        if(clienteOptional.isEmpty()){
            throw new NoSuchElementException();
        }
        return new ClienteDTO(clienteOptional.get());
    }

    public Cliente obterPorCpfModel(Long cpf) {
        Optional<Cliente> clienteOptional = clienteRepository.findByCpf(cpf);
        if(clienteOptional.isEmpty()){
            throw new NoSuchElementException();
        }
        return clienteOptional.get();
    }

    public Cliente salvar(ClienteForm clienteForm) throws InstanceAlreadyExistsException {
        Cliente cliente = clienteForm.formToCliente();
        Optional<Cliente> clienteOptionalCpf = clienteRepository.findByCpf(cliente.getCpf());
        Optional<Cliente> clienteOptionalEmail = clienteRepository.findByEmail(cliente.getEmail());
        if(clienteOptionalCpf.isPresent() || clienteOptionalEmail.isPresent()){
            throw new InstanceAlreadyExistsException();
        }
        clienteRepository.save(cliente);
        return cliente;
    }

    public Cliente atualizar(ClienteAtualizarForm clienteAtualizarForm, Long cpf) {
        Optional<Cliente> clienteOptional = clienteRepository.findByCpf(cpf);
        if(clienteOptional.isEmpty()){
            throw new NoSuchElementException();
        }
        Cliente cliente = clienteAtualizarForm.atualizar(cpf, clienteRepository);
        clienteRepository.save(cliente);
        return cliente;
    }

    public void deletar(Long cpf) {
        Optional<Cliente> clienteOptional = clienteRepository.findByCpf(cpf);
        if(clienteOptional.isEmpty()){
            throw new NoSuchElementException();
        }
//        enderecoService.deletetarTodosEnderecosDoClienteDeletado(cpf);
        clienteRepository.deleteById(clienteOptional.get().getId());
    }

}
