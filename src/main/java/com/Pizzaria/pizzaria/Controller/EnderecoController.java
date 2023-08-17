package com.Pizzaria.pizzaria.Controller;

import com.Pizzaria.pizzaria.DTO.EnderecoConverter;
import com.Pizzaria.pizzaria.DTO.EnderecoDTO;
import com.Pizzaria.pizzaria.Entity.Endereco;
import com.Pizzaria.pizzaria.Repository.EnderecoRepository;
import com.Pizzaria.pizzaria.Service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/api/Endereco")
public class EnderecoController {
    @Autowired
    private EnderecoRepository Repository;
    @Autowired
    private EnderecoService Service;

    @GetMapping("/listar")
    public ResponseEntity<List<Endereco>> listar(){
        List<Endereco> listartudo = Service.listartudo();
        return ResponseEntity.ok(listartudo);
    }
    @GetMapping("/lista")
    public ResponseEntity<List<EnderecoDTO>> lista() {
        List<Endereco> listaEnderecos = Service.listartudo();
        List<EnderecoDTO> listaEnderecosDTO = EnderecoConverter.toDtoList(listaEnderecos);
        return ResponseEntity.ok(listaEnderecosDTO);
    }

    @GetMapping("/lista/id/{id}")
    public ResponseEntity<?> listaId(@PathVariable(value = "id") Long id) {
        Endereco endereco = Repository.findById(id).orElse(null);

        if (endereco == null) {
            return ResponseEntity.badRequest().body(" <<ERRO>>: valor não encontrado.");
        }

        EnderecoDTO enderecoDTO = EnderecoConverter.toDto(endereco);

        return ResponseEntity.ok(enderecoDTO);
    }
    @GetMapping("/lista/ativo/{ativo}")
    public ResponseEntity<List<EnderecoDTO>> listaAtivo(@PathVariable boolean ativo) {
        List<Endereco> listaAtivo = Repository.findByAtivo(ativo);
        List<EnderecoDTO> listaAtivoDTO = EnderecoConverter.toDtoList(listaAtivo);

        return ResponseEntity.ok(listaAtivoDTO);
    }
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody EnderecoDTO cadastroDTO) {
        try {
            Endereco endereco = EnderecoConverter.toEntity(cadastroDTO);
            this.Service.cadastrar(endereco);
            return ResponseEntity.ok("Cadastro feito com sucesso");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("ERRO: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("ERRO: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        Optional<Endereco> deletarId = Repository.findById(id);
        if (deletarId.isPresent()) {
            Repository.deleteById(id);
            return ResponseEntity.ok("Apagado com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/put/id/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody EnderecoDTO dto) {
        try {
            Endereco enderecoAtualizado = EnderecoConverter.toEntity(dto);
            this.Service.atualizar(id, enderecoAtualizado);
            return ResponseEntity.ok().body("Atualizado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
