package com.pizzaria.controller;


import com.pizzaria.service.FuncionarioService;
import com.pizzaria.dto.FuncionarioConverter;
import com.pizzaria.dto.FuncionarioDTO;
import com.pizzaria.entity.Funcionario;
import com.pizzaria.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Controller
@RequestMapping(value = "/api/Funcionario")
@CrossOrigin(origins = "http://localhost:4200")
public class FuncionarioController {
    @Autowired
private FuncionarioRepository funcionarioRepository;
    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping("/lista")
    public ResponseEntity<List<FuncionarioDTO>> lista() {
        List<Funcionario> listas = funcionarioService.listartudo();
        List<FuncionarioDTO> listasDTO = FuncionarioConverter.toDTOList(listas);
        return ResponseEntity.ok(listasDTO);
    }
    @GetMapping("/lista/id/{id}")
    public ResponseEntity<FuncionarioDTO> listaId(@PathVariable(value = "id") Long id) {
        Funcionario listaid = funcionarioRepository.findById(id).orElse(null);
        if (listaid == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        FuncionarioDTO listaDTO = FuncionarioConverter.toDTO(listaid);
        return ResponseEntity.ok(listaDTO);
    }
    @GetMapping("/lista/ativo/{ativo}")
    public ResponseEntity<List<FuncionarioDTO>> listaAtivo(@PathVariable boolean ativo) {
        List<Funcionario> listaAtivo = funcionarioRepository.findByAtivo(ativo);
        List<FuncionarioDTO> listaAtivoDTO = FuncionarioConverter.toDTOList(listaAtivo);
        return ResponseEntity.ok(listaAtivoDTO);
    }
    @PostMapping("/cadastrar")
    public ResponseEntity<Map<String, String>> cadastrar(@RequestBody FuncionarioDTO cadastroDTO) {
        try {
            Funcionario cad = FuncionarioConverter.toEntity(cadastroDTO);
            this.funcionarioService.cadastrar(cad);
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
        Optional<Funcionario> deletarId = funcionarioRepository.findById(id);
        if (deletarId.isPresent()) {
            funcionarioRepository.deleteById(id);
            return ResponseEntity.ok("Apagado com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/put/id/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody FuncionarioDTO dto) {
        try {
            Funcionario atualizado = FuncionarioConverter.toEntity(dto);
            this.funcionarioService.atualizar(id, atualizado);
            return ResponseEntity.ok().body("Atualizado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



}

