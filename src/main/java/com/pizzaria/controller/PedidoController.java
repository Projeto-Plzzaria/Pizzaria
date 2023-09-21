package com.pizzaria.controller;

import com.pizzaria.service.PedidoService;
import com.pizzaria.dto.PedidoConverter;
import com.pizzaria.dto.PedidoDTO;
import com.pizzaria.entity.Pedido;
import com.pizzaria.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/api/Pedido")
public class PedidoController {

        @Autowired
        private PedidoRepository pedidoRepository;
        @Autowired
        private PedidoService pedidoService;

        @GetMapping("/listar")
        public ResponseEntity<List<Pedido>> listar(){
        List<Pedido> listartudo = pedidoService.listartudo();
        return ResponseEntity.ok(listartudo);
    }
    @GetMapping("/lista")
    public ResponseEntity<List<PedidoDTO>> lista() {
        List<Pedido> listas = pedidoService.listartudo();
        List<PedidoDTO> listasDTO = PedidoConverter.toDTOList(listas);
        return ResponseEntity.ok(listasDTO);
    }
    @GetMapping("/lista/id/{id}")
    public ResponseEntity<PedidoDTO> listaId(@PathVariable(value = "id") Long id) {
        Pedido listaid = pedidoRepository.findById(id).orElse(null);

        if (listaid == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        PedidoDTO listaDTO = PedidoConverter.toDTO(listaid);
        return ResponseEntity.ok(listaDTO);
    }
    @GetMapping("/lista/ativo/{ativo}")
    public ResponseEntity<List<PedidoDTO>> listaAtivo(@PathVariable boolean ativo) {
        List<Pedido> listaAtivo = pedidoRepository.findByAtivo(ativo);
        List<PedidoDTO> listaAtivoDTO = PedidoConverter.toDTOList(listaAtivo);
        return ResponseEntity.ok(listaAtivoDTO);
    }
    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody PedidoDTO cadastroDTO) {
        try {
            Pedido cadastro = PedidoConverter.toEntity(cadastroDTO);
            this.pedidoService.cadastrar(cadastro);
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
        Optional<Pedido> deletarId = pedidoRepository.findById(id);
        if (deletarId.isPresent()) {
            pedidoRepository.deleteById(id);
            return ResponseEntity.ok("Apagado com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/put/id/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody PedidoDTO dto) {
        try {
            Pedido Atualizado = PedidoConverter.toEntity(dto);
            this.pedidoService.atualizar(id, Atualizado);
            return ResponseEntity.ok().body("Atualizado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    }
