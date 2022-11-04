package br.com.bluesoft.alucar.controller;

import br.com.bluesoft.alucar.model.Cliente;
import br.com.bluesoft.alucar.model.dto.ClienteDTO;
import br.com.bluesoft.alucar.repository.ClienteRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    @GetMapping
    public List<ClienteDTO> getAllClientes(){
        List<Cliente> clientes = clienteRepository.findAll();
        return ClienteDTO.clienteToDto(clientes);
    }


}
