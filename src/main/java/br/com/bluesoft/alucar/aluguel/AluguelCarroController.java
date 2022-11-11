package br.com.bluesoft.alucar.aluguel;

import br.com.bluesoft.alucar.aluguel.model.Aluguel;
import br.com.bluesoft.alucar.aluguel.model.dto.AluguelDTO;
import br.com.bluesoft.alucar.aluguel.model.form.AluguelCarroForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/aluguel")
public class AluguelCarroController {


    private final AluguelCarroService aluguelCarroService;

    public AluguelCarroController(AluguelCarroService aluguelCarroService) {
        this.aluguelCarroService = aluguelCarroService;
    }

    @GetMapping
    public ResponseEntity<List<AluguelDTO>> pegarAlugueisAtivos(){
        try{
            List<AluguelDTO> alugueis = aluguelCarroService.obterTodosAtivos();
            return ResponseEntity.ok().body(alugueis);
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/lixeira")
    public ResponseEntity<List<AluguelDTO>> pegarAlugueisApagados(){
        try{
            List<AluguelDTO> alugueis = aluguelCarroService.obterTodosApagados();
            return ResponseEntity.ok().body(alugueis);
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }

    //todo método get para buscar aluguel filtrando por cliente
    //todo método get para buscar aluguel filtrando por vendedor
    //todo método get para buscar aluguel filtrando por carro

    @PostMapping("/alugar")
    @Transactional
    public ResponseEntity<AluguelDTO> cadastrarVenda(@RequestBody AluguelCarroForm aluguelCarroForm, UriComponentsBuilder uriBuilder) {

        try{
            Aluguel aluguel = aluguelCarroService.salvar(aluguelCarroForm);


            URI uri = uriBuilder.path("/aluguel/{id}")
                    .buildAndExpand(aluguel.getId())
                    .toUri();
            return ResponseEntity.created(uri).body(new AluguelDTO(aluguel));
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/apagar/{id}")
    public ResponseEntity<?> apagarAluguel(@PathVariable Long id){
        aluguelCarroService.deletarLogicamente(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletarAluguel(@PathVariable Long id){
        aluguelCarroService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}