package com.pizzaria.controller;

import com.pizzaria.dto.BebidaConverter;
import com.pizzaria.dto.BebidaDTO;
import com.pizzaria.entity.Bebida;
import com.pizzaria.repository.ClienteRepository;
import com.pizzaria.dto.ClienteConverter;
import com.pizzaria.dto.ClienteDTO;
import com.pizzaria.entity.Cliente;
import com.pizzaria.service.ClienteService;
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
@RequestMapping(value = "/api/Cliente")
@CrossOrigin(origins = "http://localhost:4200")
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
    @GetMapping("/lista/id/{id}")
    public ResponseEntity<ClienteDTO> listaId(@PathVariable(value = "id") Long id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);

        if (cliente == null) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
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
    public ResponseEntity<Map<String, String>> cadastrar(@RequestBody ClienteDTO cadastroDTO) {
        try {
            Cliente cad = ClienteConverter.toEntity(cadastroDTO);
            this.clienteService.cadastrar(cad);
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
        Optional<Cliente> deletarId = clienteRepository.findById(id);
        if (deletarId.isPresent()) {
            clienteRepository.deleteById(id);
            return ResponseEntity.ok("Apagado com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/put/id/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody ClienteDTO dto) {
        try {
            Cliente clienteAtualizado = ClienteConverter.toEntity(dto);
            this.clienteService.atualizar(id, clienteAtualizado);
            return ResponseEntity.ok().body("Atualizado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
