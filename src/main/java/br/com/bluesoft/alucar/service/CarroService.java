package br.com.bluesoft.alucar.service;

import br.com.bluesoft.alucar.model.Carro;
import br.com.bluesoft.alucar.model.dto.CarroDTO;
import br.com.bluesoft.alucar.model.form.CarroForm;
import br.com.bluesoft.alucar.model.form.atualizar.CarroAtualizarForm;
import br.com.bluesoft.alucar.repository.CarroRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CarroService {

    CarroRepository carroRepository;

    public CarroService(CarroRepository carroRepository){
        this.carroRepository = carroRepository;
    }

    public List<CarroDTO> obterCarros(){
        List<Carro> carros = carroRepository.findAll();
        return CarroDTO.carroToDto(carros);
    }


    public Carro salvar(CarroForm carroForm) {
        Carro carro = carroForm.formToCarro();
        carroRepository.save(carro);
        return carro;

    }

    public Carro atualizar(CarroAtualizarForm carroAtualizarForm, String placa) {
        Optional<Carro> carroOptional = carroRepository.findByPlaca(placa.toUpperCase());

        if(carroOptional.isEmpty()){
            throw new NoSuchElementException();
        }
        Carro carro = carroAtualizarForm.atualizar(placa, carroRepository);
        carroRepository.save(carro);
        return carro;

    }

    public CarroDTO obterPorPlaca(String placa) {
        Optional<Carro> carroOptional = carroRepository.findByPlaca(placa.toUpperCase());
        if(carroOptional.isEmpty()){
            throw new NoSuchElementException();
        }
        return new CarroDTO(carroOptional.get());
    }

    public Carro obterPorPlacaModel(String placa) {
        Optional<Carro> carroOptional = carroRepository.findByPlaca(placa.toUpperCase());
        if(carroOptional.isEmpty()){
            throw new NoSuchElementException("Carro não encontrado");
        }
        return carroOptional.get();
    }

    public void deletar(String placa) {
        Optional<Carro> carroOptional = carroRepository.findByPlaca(placa.toUpperCase());
        if(carroOptional.isEmpty()){
            throw new NoSuchElementException();
        }
        carroRepository.deleteById(placa);
    }
}
