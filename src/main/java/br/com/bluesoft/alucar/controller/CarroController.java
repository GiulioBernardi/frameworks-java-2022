package br.com.bluesoft.alucar.controller;

import br.com.bluesoft.alucar.model.Carro;
import br.com.bluesoft.alucar.model.dto.CarroDTO;
import br.com.bluesoft.alucar.repository.CarroRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/carros")
public class CarroController {

    CarroRepository carroRepository;

    public CarroController(CarroRepository carroRepository){
        this.carroRepository = carroRepository;
    }

    @GetMapping
    public List<CarroDTO> getAllCarros(){
        List<Carro> carros = carroRepository.findAll();
        return CarroDTO.carroToDto(carros);
    }


}
