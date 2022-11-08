package br.com.bluesoft.alucar.cliente;

import br.com.bluesoft.alucar.cliente.model.Cliente;
import br.com.bluesoft.alucar.cliente.model.dto.ClienteDTO;
import br.com.bluesoft.alucar.cliente.model.form.ClienteForm;
import br.com.bluesoft.alucar.cliente.model.form.atualizar.ClienteAtualizarForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.management.InstanceAlreadyExistsException;
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
    public ResponseEntity<List<ClienteDTO>> getAllClientes(){
        try{
            List<ClienteDTO> clienteDTOList = clienteService.obterClientes();
            return ResponseEntity.ok().body(clienteDTOList);
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
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
        try{
            Cliente cliente = clienteService.salvar(clienteForm);

            URI uri = uriBuilder.path("/clientes/{cpf}")
                    .buildAndExpand(cliente.getCpf())
                    .toUri();
            return ResponseEntity.created(uri).body(new ClienteDTO(cliente));
        }catch (InstanceAlreadyExistsException e){
            return ResponseEntity.badRequest().build();
        }

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