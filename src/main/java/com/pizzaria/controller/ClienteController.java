package com.pizzaria.controller;


import com.pizzaria.repository.ClienteRepository;
import com.pizzaria.dto.ClienteConverter;
import com.pizzaria.dto.ClienteDTO;
import com.pizzaria.entity.Cliente;
import com.pizzaria.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/api/Cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/lista")
    public ResponseEntity<List<ClienteDTO>> lista() {
        List<Cliente> listaClientes = clienteService.listartudo();
        List<ClienteDTO> listaClientesDTO = ClienteConverter.toDtoList(listaClientes);
        return ResponseEntity.ok(listaClientesDTO);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Cliente>> listas(){
        List<Cliente> listartudo = clienteService.listartudo();
        return ResponseEntity.ok(listartudo);
    }

    @GetMapping("/lista/id/{id}")
    public ResponseEntity<?> listaId(@PathVariable(value = "id") Long id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);

        if (cliente == null) {
            return ResponseEntity.badRequest().body(" <<ERRO>>: valor não encontrado.");
        }

        ClienteDTO clienteDTO = ClienteConverter.toDto(cliente);

        return ResponseEntity.ok(clienteDTO);
    }

    @GetMapping("/lista/ativo/{ativo}")
    public ResponseEntity<List<ClienteDTO>> listaAtivo(@PathVariable boolean ativo) {
        List<Cliente> listaAtivo = clienteRepository.findByAtivo(ativo);
        List<ClienteDTO> listaAtivoDTO = ClienteConverter.toDtoList(listaAtivo);

        return ResponseEntity.ok(listaAtivoDTO);
    }


    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody ClienteDTO cadastroDTO) {
        try {
            Cliente cliente = ClienteConverter.toEntity(cadastroDTO);
            this.clienteService.cadastrar(cliente);
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
        Optional<Cliente> deletarId = clienteRepository.findById(id);
        if (deletarId.isPresent()) {
            clienteRepository.deleteById(id);
            return ResponseEntity.ok("Apagado com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/put/id/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody ClienteDTO dto) {
        try {
            Cliente clienteAtualizado = ClienteConverter.toEntity(dto);
            this.clienteService.atualizar(id, clienteAtualizado);
            return ResponseEntity.ok().body("Atualizado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
