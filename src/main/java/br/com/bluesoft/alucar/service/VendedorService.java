package br.com.bluesoft.alucar.service;

import br.com.bluesoft.alucar.model.Vendedor;
import br.com.bluesoft.alucar.model.dto.VendedorDTO;
import br.com.bluesoft.alucar.model.form.VendedorForm;
import br.com.bluesoft.alucar.model.form.atualizar.VendedorAtualizarForm;
import br.com.bluesoft.alucar.repository.VendedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class VendedorService {

    VendedorRepository vendedorRepository;

    public VendedorService(VendedorRepository vendedorRepository){
        this.vendedorRepository = vendedorRepository;
    }
    public List<VendedorDTO> obterVendedores() {
        List<Vendedor> vendedores = vendedorRepository.findAll();
        return VendedorDTO.vendedorToDto(vendedores);
    }

    public VendedorDTO obterPorCpf(Long cpf) {
        Optional<Vendedor> vendedorOptional = vendedorRepository.findByCpf(cpf);
        if(vendedorOptional.isEmpty()){
            throw new NoSuchElementException();
        }
        return new VendedorDTO(vendedorOptional.get());
    }

    public Vendedor obterPorCpfModel(Long cpf) {
        Optional<Vendedor> vendedorOptional = vendedorRepository.findByCpf(cpf);
        if(vendedorOptional.isEmpty()){
            throw new NoSuchElementException("Vendedor não encontrado");
        }
        return vendedorOptional.get();
    }

    public Vendedor salvar(VendedorForm vendedorForm) {
        Vendedor vendedor = vendedorForm.formToVendedor();
        vendedorRepository.save(vendedor);
        return vendedor;
    }

    public Vendedor atualizar(VendedorAtualizarForm vendedorAtualizarForm, Long cpf) {
        Optional<Vendedor> vendedorOptional = vendedorRepository.findByCpf(cpf);
        if(vendedorOptional.isEmpty()){
            throw new NoSuchElementException();
        }
        Vendedor vendedor = vendedorAtualizarForm.atualizar(cpf, vendedorRepository);
        vendedorRepository.save(vendedor);
        return vendedor;
    }

    public void deletar(Long cpf) {
        Optional<Vendedor> vendedorOptional = vendedorRepository.findByCpf(cpf);
        if(vendedorOptional.isEmpty()){
            throw new NoSuchElementException();
        }
        vendedorRepository.deleteById(vendedorOptional.get().getId());
    }
}
