package br.com.bluesoft.alucar.carro;

import br.com.bluesoft.alucar.carro.model.Carro;
import br.com.bluesoft.alucar.carro.model.dto.CarroDTO;
import br.com.bluesoft.alucar.carro.model.form.CarroForm;
import br.com.bluesoft.alucar.carro.model.form.atualizar.CarroAtualizarForm;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
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
        if(carros.isEmpty()){
            throw new NoSuchElementException();
        }
        return CarroDTO.carroToDto(carros);
    }


    public Carro salvar(CarroForm carroForm) throws InstanceAlreadyExistsException {
        Carro carro = carroForm.formToCarro();

        Optional<Carro> carroOptional = carroRepository.findByPlaca(carro.getPlaca());
        if(carroOptional.isPresent()){
            throw new InstanceAlreadyExistsException();
        }
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
