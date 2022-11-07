package br.com.bluesoft.alucar.controller;

import br.com.bluesoft.alucar.model.Cliente;
import br.com.bluesoft.alucar.model.dto.ClienteDTO;
import br.com.bluesoft.alucar.model.form.ClienteForm;
import br.com.bluesoft.alucar.model.form.atualizar.ClienteAtualizarForm;
import br.com.bluesoft.alucar.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    ClienteService clienteService;

    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<ClienteDTO> getAllClientes(){
        return clienteService.obterClientes();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ClienteDTO> getClientePeloCpf(@PathVariable Long cpf){
        try{
            return ResponseEntity.ok().body(clienteService.obterPorCpf(cpf));
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<ClienteDTO> cadastrarCliente(@RequestBody @Valid ClienteForm clienteForm, UriComponentsBuilder uriBuilder){
        Cliente cliente = clienteService.salvar(clienteForm);

        URI uri = uriBuilder.path("/clientes/{cpf}")
                .buildAndExpand(cliente.getCpf())
                .toUri();
        return ResponseEntity.created(uri).body(new ClienteDTO(cliente));
    }

    @PutMapping("/atualizar/{cpf}")
    @Transactional
    public ResponseEntity<ClienteDTO> atualizar(@PathVariable Long cpf, @RequestBody @Valid ClienteAtualizarForm clienteAtualizarForm){
        Cliente cliente = clienteService.atualizar(clienteAtualizarForm, cpf);
        return ResponseEntity.ok(new ClienteDTO(cliente));
    }

    @DeleteMapping("/deletar/{cpf}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long cpf){
        clienteService.deletar(cpf);
        return ResponseEntity.noContent().build();
    }
}