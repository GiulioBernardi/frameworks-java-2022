package br.com.bluesoft.alucar.service;

import br.com.bluesoft.alucar.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {


    EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository){
        this.enderecoRepository = enderecoRepository;
    }

    public void deletetarTodosEnderecosDoClienteDeletado(Long cpf) {
        enderecoRepository.deletaEnderecoPorIdCliente(cpf);
    }
}
