package com.pizzaria.controller;

import com.pizzaria.repository.BebidasRepository;
import com.pizzaria.dto.BebidaConverter;
import com.pizzaria.dto.BebidaDTO;
import com.pizzaria.entity.Bebida;
import com.pizzaria.service.BebidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/api/Bebida")
public class BebidaController {
    @Autowired
    private BebidasRepository bebidasRepository;
    @Autowired
    private BebidaService bebidaService;





    @GetMapping("/listar")
    public ResponseEntity<List<Bebida>> listas(){
        List<Bebida> listartudo = bebidaService.listartudo();
        return ResponseEntity.ok(listartudo);
    }

    @GetMapping("/lista/id/{id}")
    public ResponseEntity<?> listaId(@PathVariable(value = "id") Long id) {
        Bebida bebida = bebidasRepository.findById(id).orElse(null);
        if (bebida == null) {
            return ResponseEntity.badRequest().body(" <<ERRO>>: valor não encontrado.");
        }
        BebidaDTO bebidaDTO = BebidaConverter.toDto(bebida);
        return ResponseEntity.ok(bebidaDTO);
    }

    @GetMapping("/lista/ativo/{ativo}")
    public ResponseEntity<List<BebidaDTO>> listaAtivo(@PathVariable boolean ativo) {
        List<Bebida> listaAtivo = bebidasRepository.findByAtivo(ativo);
        List<BebidaDTO> listaAtivoDTO = BebidaConverter.toDtoList(listaAtivo);

        return ResponseEntity.ok(listaAtivoDTO);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody BebidaDTO cadastroDTO) {
        try {
            Bebida bebida = BebidaConverter.toEntity(cadastroDTO);
            this.bebidaService.cadastrar(bebida);
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
        Optional<Bebida> deletarId = bebidasRepository.findById(id);
        if (deletarId.isPresent()) {
            bebidasRepository.deleteById(id);
            return ResponseEntity.ok("Apagado com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/put/id/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody BebidaDTO dto) {
        try {
            Bebida bebidaAtualizada = BebidaConverter.toEntity(dto);
            this.bebidaService.atualizar(id, bebidaAtualizada);
            return ResponseEntity.ok().body("Atualizado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
