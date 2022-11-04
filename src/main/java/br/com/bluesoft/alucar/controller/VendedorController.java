package br.com.bluesoft.alucar.controller;


import br.com.bluesoft.alucar.model.Cliente;
import br.com.bluesoft.alucar.model.Vendedor;
import br.com.bluesoft.alucar.model.dto.ClienteDTO;
import br.com.bluesoft.alucar.model.dto.VendedorDTO;
import br.com.bluesoft.alucar.model.form.VendedorForm;
import br.com.bluesoft.alucar.model.form.atualizar.ClienteAtualizarForm;
import br.com.bluesoft.alucar.model.form.atualizar.VendedorAtualizarForm;
import br.com.bluesoft.alucar.service.VendedorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/vendedor")
public class VendedorController {

    VendedorService vendedorService;

    public VendedorController(VendedorService vendedorService){
        this.vendedorService = vendedorService;
    }

    @GetMapping
    public List<VendedorDTO> getAllVendedores(){
        return vendedorService.obterVendedores();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<VendedorDTO> getClientesPeloCpf(@PathVariable Long cpf){
        try{
            return ResponseEntity.ok().body(vendedorService.obterPorCpf(cpf));
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<VendedorDTO> cadastrarVendedor(@RequestBody @Valid VendedorForm vendedorForm, UriComponentsBuilder uriBuilder){
        Vendedor vendedor = vendedorService.salvar(vendedorForm);

        URI uri = uriBuilder.path("/vendedor/{cpf}")
                .buildAndExpand(vendedor.getCpf())
                .toUri();
        return ResponseEntity.created(uri).body(new VendedorDTO(vendedor));
    }


    @PutMapping("/atualizar/{cpf}")
    @Transactional
    public ResponseEntity<VendedorDTO> atualizar(@PathVariable Long cpf, @RequestBody @Valid VendedorAtualizarForm vendedorAtualizarForm){
        Vendedor vendedor = vendedorService.atualizar(vendedorAtualizarForm, cpf);
        return ResponseEntity.ok(new VendedorDTO(vendedor));
    }


    @DeleteMapping("/deletar/{cpf}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long cpf){
        vendedorService.deletar(cpf);
        return ResponseEntity.noContent().build();
    }
}
