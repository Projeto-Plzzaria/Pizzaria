package com.Pizzaria.pizzaria.Controller;

import com.Pizzaria.pizzaria.DTO.EnderecoConverter;
import com.Pizzaria.pizzaria.DTO.EnderecoDTO;
import com.Pizzaria.pizzaria.DTO.PedidoConverter;
import com.Pizzaria.pizzaria.DTO.PedidoDTO;
import com.Pizzaria.pizzaria.Entity.Endereco;
import com.Pizzaria.pizzaria.Entity.Pedido;
import com.Pizzaria.pizzaria.Repository.PedidoRepository;
import com.Pizzaria.pizzaria.Service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/api/Pedido")
public class PedidoController {

        @Autowired
        private PedidoRepository Repository;
        @Autowired
        private PedidoService Service;

        @GetMapping("/listar")
        public ResponseEntity<List<Pedido>> listar(){
        List<Pedido> listartudo = Service.listartudo();
        return ResponseEntity.ok(listartudo);
    }
    @GetMapping("/lista")
    public ResponseEntity<List<PedidoDTO>> lista() {
        List<Pedido> listas = Service.listartudo();
        List<PedidoDTO> listasDTO = PedidoConverter.toDTOList(listas);
        return ResponseEntity.ok(listasDTO);
    }
    @GetMapping("/lista/id/{id}")
    public ResponseEntity<?> listaId(@PathVariable(value = "id") Long id) {
        Pedido listaid = Repository.findById(id).orElse(null);

        if (listaid == null) {
            return ResponseEntity.badRequest().body(" <<ERRO>>: valor não encontrado.");
        }

        PedidoDTO listaDTO = PedidoConverter.toDTO(listaid);
        return ResponseEntity.ok(listaDTO);
    }
    @GetMapping("/lista/ativo/{ativo}")
    public ResponseEntity<List<PedidoDTO>> listaAtivo(@PathVariable boolean ativo) {
        List<Pedido> listaAtivo = Repository.findByAtivo(ativo);
        List<PedidoDTO> listaAtivoDTO = PedidoConverter.toDTOList(listaAtivo);
        return ResponseEntity.ok(listaAtivoDTO);
    }
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody PedidoDTO cadastroDTO) {
        try {
            Pedido cadastro = PedidoConverter.toEntity(cadastroDTO);
            this.Service.cadastrar(cadastro);
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
        Optional<Pedido> deletarId = Repository.findById(id);
        if (deletarId.isPresent()) {
            Repository.deleteById(id);
            return ResponseEntity.ok("Apagado com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/put/id/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody PedidoDTO dto) {
        try {
            Pedido Atualizado = PedidoConverter.toEntity(dto);
            this.Service.atualizar(id, Atualizado);
            return ResponseEntity.ok().body("Atualizado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    }
