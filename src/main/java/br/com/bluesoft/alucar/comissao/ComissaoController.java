package br.com.bluesoft.alucar.comissao;

import br.com.bluesoft.alucar.comissao.model.dto.ComissaoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/comissões")
public class ComissaoController {

    ComissaoService comissaoService;

    public ComissaoController(ComissaoService comissaoService){
        this.comissaoService = comissaoService;
    }


    @GetMapping
    public ResponseEntity<List<ComissaoDTO>> getComissoes(){
        try{
            List<ComissaoDTO> comissaoDTOS = comissaoService.buscaAgrupaPorVendedor();
            return ResponseEntity.ok().body(comissaoDTOS);
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ComissaoDTO> getComissaoPorCof(@PathVariable Long cpf){
        try{
            ComissaoDTO comissao = comissaoService.buscaComissaoPorCpf(cpf);
            return ResponseEntity.ok().body(comissao);
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }
}