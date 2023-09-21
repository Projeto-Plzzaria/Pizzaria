package com.pizzaria.controller;

import com.pizzaria.dto.EnderecoConverter;
import com.pizzaria.dto.EnderecoDTO;
import com.pizzaria.entity.Endereco;
import com.pizzaria.repository.EnderecoRepository;
import com.pizzaria.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/api/Endereco")
public class EnderecoController {
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private EnderecoService enderecoService;

    @GetMapping("/lista")
    public ResponseEntity<List<EnderecoDTO>> lista() {
        List<Endereco> listaEnderecos = enderecoService.listartudo();
        List<EnderecoDTO> listaEnderecosDTO = EnderecoConverter.toDtoList(listaEnderecos);
        return ResponseEntity.ok(listaEnderecosDTO);
    }

    @GetMapping("/lista/id/{id}")
    public ResponseEntity<EnderecoDTO> listaId(@PathVariable(value = "id") Long id) {
        Endereco endereco = enderecoRepository.findById(id).orElse(null);

        if (endereco == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        EnderecoDTO enderecoDTO = EnderecoConverter.toDto(endereco);

        return ResponseEntity.ok(enderecoDTO);
    }
    @GetMapping("/lista/ativo/{ativo}")
    public ResponseEntity<List<EnderecoDTO>> listaAtivo(@PathVariable boolean ativo) {
        List<Endereco> listaAtivo = enderecoRepository.findByAtivo(ativo);
        List<EnderecoDTO> listaAtivoDTO = EnderecoConverter.toDtoList(listaAtivo);

        return ResponseEntity.ok(listaAtivoDTO);
    }
    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody EnderecoDTO cadastroDTO) {
        try {
            Endereco endereco = EnderecoConverter.toEntity(cadastroDTO);
            this.enderecoService.cadastrar(endereco);
            return ResponseEntity.ok("Cadastro feito com sucesso");
        } catch (DataIntegrityViolationException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("ERRO: " + e.getMessage());
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        Optional<Endereco> deletarId = enderecoRepository.findById(id);
        if (deletarId.isPresent()) {
            enderecoRepository.deleteById(id);
            return ResponseEntity.ok("Apagado com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/put/id/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody EnderecoDTO dto) {
        try {
            Endereco enderecoAtualizado = EnderecoConverter.toEntity(dto);
            this.enderecoService.atualizar(id, enderecoAtualizado);
            return ResponseEntity.ok().body("Atualizado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
