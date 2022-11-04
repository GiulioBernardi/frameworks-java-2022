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

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/carros")
public class CarroController {

    CarroService carroService;

    public CarroController(CarroService carroService){
        this.carroService = carroService;
    }

    @GetMapping
    public List<CarroDTO> getAllCarros(){
        return carroService.obterCarros();
    }

    @GetMapping("/{placa}")
    public CarroDTO getCarroPelaPlaca(@PathVariable String placa){
        return carroService.obterPorPlaca(placa);
    }

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<CarroDTO> cadastrarCarro(@RequestBody @Valid CarroForm carroForm, UriComponentsBuilder uriBuilder){
        Carro carro = carroService.salvar(carroForm);

        URI uri = uriBuilder.path("/carros/{placa}")
                .buildAndExpand(carro.getPlaca())
                .toUri();

        return ResponseEntity.created(uri).body(new CarroDTO(carro));
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
