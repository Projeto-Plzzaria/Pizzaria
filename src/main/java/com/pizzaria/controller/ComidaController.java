package com.pizzaria.controller;

import com.pizzaria.repository.ComidaRepository;
import com.pizzaria.dto.ComidaConverter;
import com.pizzaria.dto.ComidaDTO;
import com.pizzaria.entity.Comida;
import com.pizzaria.service.ComidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/api/Comida")
public class ComidaController {


    @Autowired
    private ComidaRepository comidaRepository;
    @Autowired
    private ComidaService comidaService;

    @GetMapping("/lista")
    public ResponseEntity<List<ComidaDTO>> lista() {
        List<Comida> listaComidas = comidaService.listartudo();
        List<ComidaDTO> listaComidasDTO = ComidaConverter.toDtoList(listaComidas);
        return ResponseEntity.ok(listaComidasDTO);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Comida>> listas(){
        List<Comida> listartudo = comidaService.listartudo();
        return ResponseEntity.ok(listartudo);
    }

    @GetMapping("/lista/id/{id}")
    public ResponseEntity<ComidaDTO> listaId(@PathVariable(value = "id") Long id) {
        Comida comida = comidaRepository.findById(id).orElse(null);

        if (comida == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        ComidaDTO comidaDTO = ComidaConverter.toDto(comida);

        return ResponseEntity.ok(comidaDTO);
    }

    @GetMapping("/lista/ativo/{ativo}")
    public ResponseEntity<List<ComidaDTO>> listaAtivo(@PathVariable boolean ativo) {
        List<Comida> listaAtivo = comidaRepository.findByAtivo(ativo);
        List<ComidaDTO> listaAtivoDTO = ComidaConverter.toDtoList(listaAtivo);

        return ResponseEntity.ok(listaAtivoDTO);
    }


    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody ComidaDTO cadastroDTO) {
        try {
            Comida comida = ComidaConverter.toEntity(cadastroDTO);
            this.comidaService.cadastrar(comida);
            return ResponseEntity.ok("Cadastro feito com sucesso");
        } catch (DataIntegrityViolationException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("ERRO: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        Optional<Comida> deletarId = comidaRepository.findById(id);
        if (deletarId.isPresent()) {
            comidaRepository.deleteById(id);
            return ResponseEntity.ok("Apagado com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/put/id/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody ComidaDTO dto) {
        try {
            Comida comidaAtualizada = ComidaConverter.toEntity(dto);
            this.comidaService.atualizar(id, comidaAtualizada);
            return ResponseEntity.ok().body("Atualizado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
