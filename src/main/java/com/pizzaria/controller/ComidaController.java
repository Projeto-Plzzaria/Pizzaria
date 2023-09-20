package com.pizzaria.controller;

import com.pizzaria.repository.ComidaRepository;
import com.pizzaria.dto.ComidaConverter;
import com.pizzaria.dto.ComidaDTO;
import com.pizzaria.entity.Comida;
import com.pizzaria.service.ComidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/api/Comida")
public class ComidaController {


    @Autowired
    private ComidaRepository Repository;
    @Autowired
    private ComidaService Service;

    @GetMapping("/lista")
    public ResponseEntity<List<ComidaDTO>> lista() {
        List<Comida> listaComidas = Service.listartudo();
        List<ComidaDTO> listaComidasDTO = ComidaConverter.toDtoList(listaComidas);
        return ResponseEntity.ok(listaComidasDTO);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Comida>> listas(){
        List<Comida> listartudo = Service.listartudo();
        return ResponseEntity.ok(listartudo);
    }

    @GetMapping("/lista/id/{id}")
    public ResponseEntity<?> listaId(@PathVariable(value = "id") Long id) {
        Comida comida = Repository.findById(id).orElse(null);

        if (comida == null) {
            return ResponseEntity.badRequest().body(" <<ERRO>>: valor não encontrado.");
        }

        ComidaDTO comidaDTO = ComidaConverter.toDto(comida);

        return ResponseEntity.ok(comidaDTO);
    }

    @GetMapping("/lista/ativo/{ativo}")
    public ResponseEntity<List<ComidaDTO>> listaAtivo(@PathVariable boolean ativo) {
        List<Comida> listaAtivo = Repository.findByAtivo(ativo);
        List<ComidaDTO> listaAtivoDTO = ComidaConverter.toDtoList(listaAtivo);

        return ResponseEntity.ok(listaAtivoDTO);
    }


    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody ComidaDTO cadastroDTO) {
        try {
            Comida comida = ComidaConverter.toEntity(cadastroDTO);
            this.Service.cadastrar(comida);
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
        Optional<Comida> deletarId = Repository.findById(id);
        if (deletarId.isPresent()) {
            Repository.deleteById(id);
            return ResponseEntity.ok("Apagado com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/put/id/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody ComidaDTO dto) {
        try {
            Comida comidaAtualizada = ComidaConverter.toEntity(dto);
            this.Service.atualizar(id, comidaAtualizada);
            return ResponseEntity.ok().body("Atualizado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
