package com.pizzaria.controller;

import com.pizzaria.repository.BebidasRepository;
import com.pizzaria.dto.BebidaConverter;
import com.pizzaria.dto.BebidaDTO;
import com.pizzaria.entity.Bebida;
import com.pizzaria.service.BebidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.HashMap;
import java.util.Map;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/api/Bebida")
@CrossOrigin(origins = "http://localhost:62313")
public class BebidaController {
    @Autowired
    private BebidasRepository bebidasRepository;
    @Autowired
    private BebidaService bebidaService;


    @GetMapping("/lista")
    public ResponseEntity<List<BebidaDTO>> lista() {
        List<Bebida> listaBebidas = bebidaService.listartudo();
        List<BebidaDTO> listaBebidasDTO = BebidaConverter.toDtoList(listaBebidas);
        return ResponseEntity.ok(listaBebidasDTO);
    }
    @GetMapping("/lista/id/{id}")
    public ResponseEntity<BebidaDTO> listaId(@PathVariable(value = "id") Long id) {
        Bebida bebida = bebidasRepository.findById(id).orElse(null);
        if (bebida == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
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
    public ResponseEntity<Map<String, String>> cadastrar(@RequestBody BebidaDTO cadastroDTO) {
        try {
            Bebida bebida = BebidaConverter.toEntity(cadastroDTO);
            this.bebidaService.cadastrar(bebida);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Cadastro feito com sucesso");
            return ResponseEntity.ok(response);
        } catch (DataIntegrityViolationException | IllegalArgumentException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "ERRO: " + e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
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
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody BebidaDTO dto) {
        try {
            Bebida bebidaAtualizada = BebidaConverter.toEntity(dto);
            this.bebidaService.atualizar(id, bebidaAtualizada);
            return ResponseEntity.ok().body("Atualizado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



}
