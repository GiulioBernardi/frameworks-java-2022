package br.com.bluesoft.alucar.controller;

import br.com.bluesoft.alucar.model.Carro;
import br.com.bluesoft.alucar.model.dto.CarroDTO;
import br.com.bluesoft.alucar.model.form.CarroForm;
import br.com.bluesoft.alucar.model.form.atualizar.CarroAtualizarForm;
import br.com.bluesoft.alucar.repository.CarroRepository;
import br.com.bluesoft.alucar.service.CarroService;
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
@RequestMapping("/carros")
public class CarroController {

    CarroService carroService;

    public CarroController(CarroService carroService){
        this.carroService = carroService;
    }

    @GetMapping
    public ResponseEntity<List<CarroDTO>> getAllCarros(){
        try{
            List<CarroDTO> carros = carroService.obterCarros();
            return ResponseEntity.ok().body(carros);
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{placa}")
    public ResponseEntity<CarroDTO> getCarroPelaPlaca(@PathVariable String placa){
        try{
            CarroDTO retornoDaService = carroService.obterPorPlaca(placa);
            return ResponseEntity.ok().body(retornoDaService);
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<CarroDTO> cadastrarCarro(@RequestBody @Valid CarroForm carroForm, UriComponentsBuilder uriBuilder){
        try{
            Carro carro = carroService.salvar(carroForm);

            URI uri = uriBuilder.path("/carros/{placa}")
                    .buildAndExpand(carro.getPlaca())
                    .toUri();

            return ResponseEntity.created(uri).body(new CarroDTO(carro));
        }catch (InstanceAlreadyExistsException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/atualizar/{placa}")
    @Transactional
    public ResponseEntity<CarroDTO> atualizar(@PathVariable String placa, @RequestBody @Valid CarroAtualizarForm carroAtualizarForm){
        Carro carro = carroService.atualizar(carroAtualizarForm, placa);
        return ResponseEntity.ok(new CarroDTO(carro));
    }

    @DeleteMapping("/deletar/{placa}")
    public ResponseEntity<?> deletar(@PathVariable String placa){
        carroService.deletar(placa);
        return ResponseEntity.noContent().build();
    }









}
