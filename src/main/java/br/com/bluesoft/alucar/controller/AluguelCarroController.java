package br.com.bluesoft.alucar.controller;

import br.com.bluesoft.alucar.model.Aluguel;
import br.com.bluesoft.alucar.model.dto.AluguelDTO;
import br.com.bluesoft.alucar.model.form.AluguelCarroForm;
import br.com.bluesoft.alucar.repository.AluguelRepository;
import br.com.bluesoft.alucar.service.AluguelCarroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/aluguel")
public class AluguelCarroController {

    AluguelRepository aluguelRepository;


    AluguelCarroService aluguelCarroService;

    public AluguelCarroController(AluguelRepository aluguelRepository,
                                  AluguelCarroService aluguelCarroService) {

        this.aluguelRepository = aluguelRepository;
        this.aluguelCarroService = aluguelCarroService;
    }

    @PostMapping("/alugar")
    @Transactional
    public ResponseEntity<AluguelDTO> cadastrarVenda(@RequestBody AluguelCarroForm vendaCarroForm, UriComponentsBuilder uriBuilder) {

        try{
            Aluguel aluguel = aluguelCarroService.salvar(vendaCarroForm);


            URI uri = uriBuilder.path("/aluguel/{id}")
                    .buildAndExpand(aluguel.getId())
                    .toUri();
            return ResponseEntity.created(uri).body(new AluguelDTO(aluguel));
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }

    }
}