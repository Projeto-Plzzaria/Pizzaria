package com.pizzaria.controller;

import com.pizzaria.service.FuncionarioService;
import com.pizzaria.dto.FuncionarioConverter;
import com.pizzaria.dto.FuncionarioDTO;

import com.pizzaria.entity.Funcionario;

import com.pizzaria.repository.FuncionarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/api/Funcionario")
public class FuncionarioController {
    @Autowired
private FuncionarioRepository funcionarioRepository;
    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping("/listar")
    public ResponseEntity<List<Funcionario>> listar(){
        List<Funcionario> listartudo = funcionarioService.listartudo();
        return ResponseEntity.ok(listartudo);
    }
    @GetMapping("/lista")
    public ResponseEntity<List<FuncionarioDTO>> lista() {
        List<Funcionario> listas = funcionarioService.listartudo();
        List<FuncionarioDTO> listasDTO = FuncionarioConverter.toDTOList(listas);
        return ResponseEntity.ok(listasDTO);
    }
    @GetMapping("/lista/id/{id}")
    public ResponseEntity<?> listaId(@PathVariable(value = "id") Long id) {
        Funcionario listaid = funcionarioRepository.findById(id).orElse(null);

        if (listaid == null) {
            return ResponseEntity.badRequest().body(" <<ERRO>>: valor não encontrado.");
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
    public ResponseEntity<?> cadastrar(@RequestBody FuncionarioDTO cadastroDTO) {
        try {
            Funcionario cadastro = FuncionarioConverter.toEntity(cadastroDTO);
            this.funcionarioService.cadastrar(cadastro);
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
        Optional<Funcionario> deletarId = funcionarioRepository.findById(id);
        if (deletarId.isPresent()) {
            funcionarioRepository.deleteById(id);
            return ResponseEntity.ok("Apagado com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/put/id/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody FuncionarioDTO dto) {
        try {
            Funcionario Atualizado = FuncionarioConverter.toEntity(dto);
            this.funcionarioService.atualizar(id, Atualizado);
            return ResponseEntity.ok().body("Atualizado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}